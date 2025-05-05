![Static Badge](https://img.shields.io/badge/Android-green) 
![Static Badge](https://img.shields.io/badge/Kotlin-mauve)
![Static Badge](https://img.shields.io/badge/latest%20release:-1.3.0-red)
![Static Badge](https://img.shields.io/badge/jitpackio-black)



## Email Sender: Effortlessly Send Emails and Verification Codes in Your Android App

This developer-friendly library simplifies sending emails with attachment file or without attachments and verification codes (OTPs) directly from your Android project. It utilizes the well-known Simple Mail Transfer Protocol (SMTP) for reliable email delivery.

Key Features:

 - Effortless Integration: Add the library to your project with just a few lines in your gradle files.
 - Secure Email Sending: Send emails from any Gmail account with proper security measures in place. (Important: Don't forget to follow the instructions from [link](https://www.gmass.co/blog/gmail-smtp) to enable SMTP on your sender Gmail account.)
 - Flexible Functionality: Choose between sending regular emails or generating OTPs for verification purposes.

# Getting Started:

## Set up Gradle:
 - Add this at the end of your repositories (**settings.gradle**)
    ```
    repositories {
        ....
        maven {
            url = uri("https://jitpack.io")
        }
    }
    ```
 - Add the dependency 
 
     ```
      android {
        ...
        packaging {
            resources {
                excludes += "META-INF/NOTICE.md"
                excludes += "META-INF/LICENSE.md"
            }
        }
      }
      dependencies {
            implementation "com.github.MohamedKandel:email-sender:latest-release"
        }
    ```
# Simple Usage:

The library provides two convenient methods for sending emails and generating OTPs:<br/>

 - ```sendEmail(context, senderMail, senderPassword, displayName, receiverMail, subject, body, funToExecAfterSent)```<br/>
   This method allows you to send emails with customizable sender details, subject, body, and a callback function for post-sending actions.<br/>
 - ```sendEmailAttach(senderMail,senderPassword,displayName,receiverMail,subject,bodyText,attachmentPath,funToExecAfterSent) send email with attachment```<br/>
 - ```sendOTP(context, senderMail, senderPassword, displayName, OTPLength, receiverMail, funToExecAfterSent)```<br/> Generate OTP codes with this method. Specify the desired OTP length and receive the generated code as a String.

# Remember:
 - Always use a secure, encrypted password for senderPassword.
 - handle all cases to get path of selected file (not uri) to send file path to function which attach file and if you get wrong path for file email won't send, get it from cache directory.
 - if you used FileHelper class don't forget to check if any deprecation and handle it with your own way.

# Compatibility:

This library is compatible with Android versions from API 24 (Android 7) to API 34 (Android 14).

# Educational Use:

This library is primarily intended for educational purposes. For cross-platform applications (Android and iOS), consider exploring platform-specific APIs.

# Contact:

Developed by Mohamed Kandeel. Feel free to reach out via email (mohamed.hossam7799@gmail.com) with suggestions or feature requests.

Thank you for choosing Email Sender!
