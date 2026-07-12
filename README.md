# UsefulBlog Android

This is a Android client application of [Useful IT Blog](https://github.com/yymin1022/UsefulBlog)

Renders blog posts written in markdown, with customized renderer based on [Markwon Android](https://github.com/noties/Markwon)

## Project Overview

> This diagram is generated with Claude

<img src="https://github.com/user-attachments/assets/201f6444-3b35-42b6-a641-2e0478785f95" width="70%" />

- App Module - Compose based UI, ViewModel
- Domain Module - Defines repository interface, DTO
- Data Module - Implement repository with [Retrofit](https://github.com/square/retrofit), retrives post data from API Server 

## Get Started

1. Clone this repository on your local environment
   ```bash
   yong@ubuntu-server ~/ :$ git clone https://github.com/yymin1022/UsefulBlog_Android.git
   ```
2. Build with gradle, and install to your own device
   ```bash
   yong@ubuntu-server ~/ :$ cd UsefulBlog_Android
   yong@ubuntu-server ~/UsefulBlog_Android/ :$ ./gradlew assembleDebug
   yong@ubuntu-server ~/UsefulBlog_Android/ :$ adb install app/build/outputs/apk/app-debug.apk
   ```

## Team Members

| [유용민](https://github.com/yymin1022) |
|---|
| <img src="https://github.com/GDSC-CAU/Vridge-Android/assets/62137001/1904f22f-6086-4bc9-8a9d-2f6875b117fe" width="150" /> |
| Android |
| Developer |

## Screenshots

<p align="left">
  <img src="https://github.com/user-attachments/assets/436734a6-d8d1-46b0-9527-454ddbdfdb8f" width=30%>
  <img src="https://github.com/user-attachments/assets/6b74a850-ac8d-42f8-87e9-3449d67f2b9c" width=30%>
  <img src="https://github.com/user-attachments/assets/07f3e863-756b-4b28-b90d-ec651b934f65" width=30%>
</p>

## Useful links

- [Useful IT Blog](https://dev-lr.com)
- [GitHub](https://github.com/yymin1022)
- [Instagram](https://instagram.com/useful_min)
- [LinkedIn](https://linkedin.com/in/yymin1022)
