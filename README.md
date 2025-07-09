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

![1 SplashScreen](https://github.com/user-attachments/assets/0f45167e-c489-44a1-89c2-8c0b737c593f)
![2 Intro1](https://github.com/user-attachments/assets/fd45801c-f649-44bc-bb95-81c1da22c3e6)
![3 Intro2](https://github.com/user-attachments/assets/de938081-1d86-481a-a99f-63a284d2b751)
![4 Intro3](https://github.com/user-attachments/assets/5fe57018-26d7-4a72-ac94-119ed16487e6)
![5 Intro4](https://github.com/user-attachments/assets/93930843-dd8e-42b9-9727-ca242670cfda)
![6 SingUp](https://github.com/user-attachments/assets/1cef92e6-cd2d-4cc8-9a98-0224678b7f09)
![7 MobileVerification](https://github.com/user-attachments/assets/64561dcf-a1de-4411-820d-4a18f66f7a57)
![8 OTPVerification](https://github.com/user-attachments/assets/c0961a22-cd9f-408d-b90c-d864a2911737)
![9 Login](https://github.com/user-attachments/assets/aab34607-fe8e-41bf-92f2-98f338be7517)
![10 CreatePass](https://github.com/user-attachments/assets/92754cb6-0f51-4a6e-8df8-3b3fa28f77f3)
![11 SetMPin](https://github.com/user-attachments/assets/d8fa3eef-2541-403f-aa7c-b201f533ffe7)
![12 ReEnterMpin](https://github.com/user-attachments/assets/a6059266-8b9f-4adb-9c94-659a1f7c69b2)
![13 Instructions](https://github.com/user-attachments/assets/b7d04e55-c740-43ca-961f-c2ab286f8d8f)
![14 EditProfile](https://github.com/user-attachments/assets/067af9ec-1a69-4179-9cf6-c8c584901c80)
![15 Profile](https://github.com/user-attachments/assets/842285a5-b1d6-4023-90cb-8e623f8cfec6)
![16 Security](https://github.com/user-attachments/assets/d37a877c-b4e7-4995-b999-00e544fb402b)
![17 Permissions](https://github.com/user-attachments/assets/b689201b-2277-4f42-8c8b-baeec304b70d)
![18 Services](https://github.com/user-attachments/assets/17ac43ad-0344-48ea-84a3-a2b90a2393a0)
![19 ForgetPass](https://github.com/user-attachments/assets/9a871d94-6e42-443c-88be-116e3a0862c1)



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

> **Authors**: Ayush Sunil Upase  
> **Institute**: Bharati Vidyapeeth Institute of Technology  
> **Academic Year**: 2021-2022  
> **Published in IJARSCT**: DOI: [10.48175/IJARSCT-3830](https://doi.org/10.48175/IJARSCT-3830)
---
> **Certificate**: (https://drive.google.com/file/d/18jFMb84IMfPPaMaPBdEVyYNsg80evEh9/view?usp=sharing)
