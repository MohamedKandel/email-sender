# Email Sender
This library is built to sending unlimited emails and verification codes<br>
this library uses gmail smtp to send email all you need to import library by the following instructions :-<br>
***settings.gradle***
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
```
***build.gradle(Module:app)***
```
plugins {
    ..
}

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
    implementation ("com.github.MohamedKandel:email-sender:1.2.1")
    // other dependencies
}
```
after that you be able to use library easily into your project<br>
library has two methods till now (send mail - send otp)<br>
this is send mail method
```
fun sendEmail(
        context: Context, senderMail: String,
        senderPassword: String, displayName: String,
        receiverMail: String, subject: String,
        body: String, funToExecAfterSent: () -> Unit
    )
```
you pass mail which you want to send email from, email password you need to add hash password not ***normal password***
you can learn more about how to get hash password for gmail from this link
