package com.mkandeel.emailsender

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.mkandeel.email_sender.Sender

class MainActivity : AppCompatActivity() {

    private var filePath = ""

    private val pickFileLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                filePath = FileHelper.getFilePathFromUri(this,it).toString()
                Log.d("File path", filePath)
                Toast.makeText(this,filePath,Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_attach = findViewById<Button>(R.id.btn_attach)
        val btn_send = findViewById<Button>(R.id.btn_send)

        btn_attach.setOnClickListener {
            pickFileLauncher.launch("*/*")
        }

        btn_send.setOnClickListener {
            Sender.sendEmailAttach(
                "example@gmail.com",
                "#############",
                "display name",
                "example@example.com",
                "Attach file",
                "This email has been sent with attachment file",
                filePath
            ) {
                runOnUiThread{
                    Toast.makeText(this,"Email sent",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}