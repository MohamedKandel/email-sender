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


object Sender {
    fun sendEmail(
        context: Context, senderMail: String,
        senderPassword: String, displayName: String,
        receiverMail: String, subject: String,
        body: String, funToExecAfterSent: ()-> Unit
    ) {
        val properties = System.getProperties()
        properties["mail.smtp.host"] = "smtp.gmail.com"
        properties["mail.smtp,port"] = "465"
        properties["mail.smtp.ssl.enable"] = "true"
        properties["mail.smtp.auth"] = "true"

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
                    Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
                }
            }
            thread.start()
        } catch (ex: Exception) {
            Toast.makeText(context,"Email sent successfully",Toast.LENGTH_SHORT).show()
        }
    }
}