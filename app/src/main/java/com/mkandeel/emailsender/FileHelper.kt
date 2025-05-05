package com.mkandeel.emailsender

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object FileHelper {
    fun getFilePathFromUri(context: Context, uri: Uri): String? {
        return copyFileToCache(context, uri)
    }

    // Copy file to cache to ensure accessibility
    private fun copyFileToCache(context: Context, uri: Uri): String? {
        val fileName = getFileName(context, uri)
        val tempFile = File(context.cacheDir, fileName)

        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(tempFile)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return tempFile.absolutePath
    }

    // Extract file name from URI
    private fun getFileName(context: Context, uri: Uri): String {
        var fileName = "temp_file"
        val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (index != -1) {
                    fileName = it.getString(index)
                }
            }
        }
        return fileName
    }
}