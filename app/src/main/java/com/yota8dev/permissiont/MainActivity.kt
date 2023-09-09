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
            /* English:
            * You can obtain permissions through the following syntax.
            * The second parameter (Manifest. permission. XXX) can be any number of permissions,
            * and the lambda expression is used for processing permission requests
            * */

            /* Chinese:
            * 您可以通过如下语法来获取权限,
            * 第二个参数(Manifest.permission.XXX)可以为任意个数的权限,
            * lambda表达式中进行申请权限的处理
            * */
            PermissionT.request(this, Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
                if (allGranted) {
                    gerPermission()
                } else {
                    Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // call phone permission
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