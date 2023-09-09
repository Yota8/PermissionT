# About
PermissionT: an android library for easily request permissions 



# README.md

- <a href="README/README.de.md">Deutsch</a>

- <a href="README/README.en.md">English</a>

- <a href="README/README.es.md">Español</a>

- <a href="README/README.fr.md">Français</a>

- <a href="README/README.jp.md">日本語</a>

- <a href="README/README.zh_CN.md">简体中文</a>

- <a href="README/README.zn_TW.md">繁体中文</a>





# Implementation

```css
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

```css
dependencies {
	    implementation 'com.github.Yota8:PermissionT:1.0.0'
}
```





## Usage

```kotlin
PermissionT.request(this, Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
    // After obtaining all the permissions you need, proceed with the relevant operations for the permissions you requested
    if (allGranted) {
        // Actions after obtaining permissions
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
