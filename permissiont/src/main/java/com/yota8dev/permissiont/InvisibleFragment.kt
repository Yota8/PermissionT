package com.yota8dev.permissiont

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    private var mCallback: PermissionCallback? = null

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
        mCallback?.let { it(allGranted, deniedList) }
    }

    fun requestNow(callback: PermissionCallback, vararg permissions: String) {
        mCallback = callback
        requestPermissionLauncher.launch(permissions)
    }
}