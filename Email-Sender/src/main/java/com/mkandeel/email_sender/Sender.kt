package com.mkandeel.email_sender

import android.content.Context
import android.widget.Toast
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlin.random.Random


object Sender {
    private fun generateOTP(length: Int): String {
        var otp = ""
        for (i in 0..<length) {
            val number = Random.nextInt(0, 10)
            otp += "$number"
        }
        return otp
    }

    fun sendEmail(
        context: Context, senderMail: String,
        senderPassword: String, displayName: String,
        receiverMail: String, subject: String,
        body: String, funToExecAfterSent: () -> Unit
    ) {
        val properties = System.getProperties()
        properties[CONSTATNS.HOST] = CONSTATNS.HOST_VALUE
        properties[CONSTATNS.PORT] = CONSTATNS.PORT_NUMBER
        properties[CONSTATNS.SSL] = CONSTATNS.TRUE
        properties[CONSTATNS.AUTH] = CONSTATNS.TRUE

        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(
                    senderMail,
                    senderPassword
                )
            }
        })
        val message = MimeMessage(session)
        try {
            message.setFrom(InternetAddress(senderMail, displayName))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(receiverMail))
            message.subject = subject
            message.setText(body)
            val thread = Thread {
                try {
                    Transport.send(message)
                    funToExecAfterSent()
                } catch (e: MessagingException) {
                    e.printStackTrace()
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
            thread.start()
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun sendOTP(
        context: Context, senderMail: String,
        senderPassword: String, displayName: String,
        OTPLength: Int, receiverMail: String,
        funToExecAfterSent: () -> Unit
    ): String {

        val properties = System.getProperties()
        properties[CONSTATNS.HOST] = CONSTATNS.HOST_VALUE
        properties[CONSTATNS.PORT] = CONSTATNS.PORT_NUMBER
        properties[CONSTATNS.SSL] = CONSTATNS.TRUE
        properties[CONSTATNS.AUTH] = CONSTATNS.TRUE

        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(
                    senderMail,
                    senderPassword
                )
            }
        })
        val message = MimeMessage(session)
        val otp = generateOTP(OTPLength)
        try {
            message.setFrom(InternetAddress(senderMail, displayName))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(receiverMail))
            message.subject = "Verification Code"
            message.setText("Your verification code is $otp")
            val thread = Thread {
                try {
                    Transport.send(message)
                    funToExecAfterSent()
                } catch (e: MessagingException) {
                    e.printStackTrace()
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
            thread.start()

            return otp
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
            return ex.message.toString()
        }
    }
}