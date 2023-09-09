package com.yota8dev.permissiont

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    private var callback: PermissionCallback? = null

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        val deniedList = ArrayList<String>()
        it.forEach { (permission, result) ->
            if (result != true) {
                deniedList.add(permission)
            }
        }
        val allGranted = deniedList.isEmpty()
        callback?.let { it(allGranted, deniedList) }
    }

    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissionLauncher.launch(permissions)
    }
}