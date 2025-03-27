# 🎬 Cinema-KMP  

Cinema-KMP is a **Kotlin Multiplatform (KMP)** project targeting **Android** and **iOS**, designed to showcase modern app architecture with **Compose Multiplatform**.  

---

## 🚀 How to Run  

Before running the app, add your **TMDB API key** to the `local.properties` file:  

1. Open (or create) the `local.properties` file in the root directory.  
2. Add the following line:  

   ```properties
   API_KEY=your_tmdb_api_key
   ```  
3. Save the file and run the app.  

---

## 📂 Project Structure  
* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

---

## 📌 TODO: Planned Improvements  

### 🛠 **Architecture & Code Structure**  
✅ **Refactor project into separate modules:**  
- `core` – Shared logic and utilities  
- `designsystem` – UI components and styles  
- `feature` – Screens and features

### 🚀 **Future Enhancements**  
📌 *To be decided later...* 🤔  

---
