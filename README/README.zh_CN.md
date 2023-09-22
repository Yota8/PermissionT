# 关于

PermissionT: 简化获取权限的一个Android库



# README.md

- <a href="README/README.en.md">English</a>

- <a href="README/README.zh_CN.md">中文</a>





# 依赖注入

```groovy
allprojects {
	repositories {
		'...'
		maven { url 'https://jitpack.io' }
	}
}
```

```groovy
dependencies {
	    implementation 'com.github.Yota8:PermissionT:1.0.0'
}
```





## 如何使用

```kotlin
/*
*   @param1: 发起调用的activity
*   @param2: 您所需的权限(可以为多个请求)
*   allGranted: 所有权限均成功获取
*   deniedList: 用户拒绝的权限列表
* */
PermissionT.request(this, Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
    // 获取您需要的所有权限后,进行您所请求权限的相关操作
    if (allGranted) {
        // 获取权限后的操作
        // call()
    } else {
        Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
    }
}

// private fun call() {
//     try {
//         val intent = Intent(Intent.ACTION_CALL)
//         intent.data = Uri.parse("tel:123456")
//         startActivity(intent)
//     } catch (e: Exception) {
//         e.printStackTrace()
//     }
// }
```