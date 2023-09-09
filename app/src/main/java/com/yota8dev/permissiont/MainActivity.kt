package com.yota8dev.permissiont

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {
            PermissionT.request(this, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS) { allGranted, deniedList ->
                run {
                    if (allGranted) {
                        gerPermission()
                    } else {
                        Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun gerPermission() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}