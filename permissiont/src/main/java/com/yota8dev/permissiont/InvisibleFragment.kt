package com.yota8dev.permissiont

import android.content.Context
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import java.util.Locale

typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    private var mCallback: PermissionCallback? = null
    private var mContext: Context? = null

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
        Log.d("InvisibleFragment", ":$allGranted ")
        // Dialog
        if (allGranted != true) {
            val language = getCurrentLanguage()
            val dialog = AlertDialog.Builder(mContext!!)
                .setTitle(chooseTitleByLanguage(language))
                .setMessage(chooseMessageByLanguage(language))
                .create()
            dialog.show()
        }
        mCallback?.let { it(allGranted, deniedList) }


    }

    fun requestNow(callback: PermissionCallback, context: Context, vararg permissions: String) {
        mCallback = callback
        mContext = context
        requestPermissionLauncher.launch(permissions)
    }

    private fun getCurrentLanguage(): String {
        val locale = Locale.getDefault()
        var mCurrentLanguage = locale.language
        if (mCurrentLanguage == "zh") {
            mCurrentLanguage = mCurrentLanguage + "-" + locale.country
        }
        return mCurrentLanguage
    }

    private fun chooseTitleByLanguage(mCurrentLanguage: String): Int {
        return when (mCurrentLanguage) {
            "de" -> R.string.title_de
            "en" -> R.string.title_en
            "es" -> R.string.title_es
            "fr" -> R.string.title_fr
            "jp" -> R.string.title_jp
            "zh-CN" -> R.string.title_cn
            "zh-TW" -> R.string.title_tw
            else -> R.string.title_en
        }
    }

    private fun chooseMessageByLanguage(mCurrentLanguage: String): Int {
        return when (mCurrentLanguage) {
            "de" -> R.string.if_permission_ok_de
            "en" -> R.string.if_permission_ok_en
            "es" -> R.string.if_permission_ok_es
            "fr" -> R.string.if_permission_ok_fr
            "ja" -> R.string.if_permission_ok_jp
            "zh-CN" -> R.string.if_permission_ok_cn
            "zh-TW" -> R.string.if_permission_ok_tw
            else -> R.string.if_permission_ok_en
        }
    }
}