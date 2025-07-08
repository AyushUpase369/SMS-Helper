# SMS Helper || For Offline Use 📱

SMS Helper is an **Android application** that helps users interact with their lost or forgotten phone using **offline SMS commands**. It is specially designed for emergency scenarios where internet access is not available.

## 📌 Features

- 🔒 **M-PIN protected** secure SMS control
- 📍 Get your phone’s location via SMS (even without internet)
- 📳 Switch between Silent and Ring mode remotely
- 🔔 Ring your device if misplaced
- 📇 Get contact number from your phone via SMS

## 📱 Use Case

Imagine you’ve forgotten your phone at home and urgently need a contact number. Just send a pre-formatted SMS with the M-PIN from another device, and the app will fetch and send the required details.

## 💡 Technology Stack

- **Frontend:** XML (Android UI)
- **Backend:** Java (Android SDK)
- **IDE:** Android Studio
- **Database:** Firebase Realtime Database (for authentication & settings)

## 🧠 How It Works

1. Install the app and sign up.
2. Enable the service in the app settings.
3. App listens for SMS commands in the background.
4. When a valid command with the correct M-PIN is received, the corresponding task is performed and SMS reply is sent back.

## Screenshorts Of Application

![1 SplashScreen](https://github.com/user-attachments/assets/0f2232a4-30b8-4ee1-92c4-21b20742ed38)


## 🛠 Requirements

- Android OS 5.0+ (API Level 21 or higher)
- 2GB RAM minimum
- 30MB Storage

## 📲 SMS Command Examples

| Purpose              | SMS Format                            |
|----------------------|----------------------------------------|
| Get Contact Number   | `CONTACT [name] [m-pin]`               |
| Get Location         | `LOCATION [m-pin]`                     |
| Ring Phone           | `RING [m-pin]`                         |
| Switch to Normal     | `NORMAL [m-pin]`                       |
| Switch to Silent     | `SILENT [m-pin]`                       |

## 🔐 Permissions Required

- SMS (Send & Receive)
- Location
- Contacts
- Background access

## 🚀 Future Scope

- Add lock screen password change via SMS
- Implement remote shutdown
- Integrate blockchain for better security
- Fetch mobile tower location using OpenCellId
- Support MongoDB for cloud storage

## 📜 License

This project was developed as part of a diploma curriculum under the guidance of **Prof. Mithun Mhatre**, Department of Computer Technology, Bharati Vidyapeeth Institute of Technology, Navi Mumbai.

---

> **Author**: Ayush Sunil Upase  
> **Institute**: Bharati Vidyapeeth Institute of Technology  
> **Academic Year**: 2021-2022  
> **Published in IJARSCT**: DOI: [10.48175/IJARSCT-3830](https://doi.org/10.48175/IJARSCT-3830)
---
> **Certificate**: (https://drive.google.com/file/d/18jFMb84IMfPPaMaPBdEVyYNsg80evEh9/view?usp=sharing)

