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
        setContentView(com.yota8dev.permissiont.R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {
            /*
            You can obtain permissions through the following syntax.
            * The second parameter (Manifest. permission. XXX) can be any number of permissions,
            * and the lambda expression is used for processing permission requests
            * */
            PermissionT.request(this, Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
                if (allGranted) {
                    getPermission()
                } else {
                    Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // call phone permission
    private fun getPermission() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}