GigApp – Student Gig Marketplace
  

Purpose of the App

GigApp is a mobile marketplace designed to connect students and community members with short-term job opportunities, commonly referred to as “gigs.” Inspired by the concept of a hive, GigApp promotes collaboration, economic empowerment, and community-driven growth.

Key Goals:
- Reduce youth unemployment in South Africa  
- Provide a platform for students to earn income through verified gigs  
- Foster trust and transparency between gig posters and applicants  

Design Considerations

Branding & UX
- Theme: Hive-inspired, minimalist design with honey-yellow accents  
- UI Principles: Clean layout, intuitive navigation, and accessibility for all tech literacy levels  
- Gamification: Points, badges, and gig stats to encourage engagement  

Architecture Overview

| Layer        | Technology Used 
|              |
| Frontend     | Kotlin + Jetpack Compose 
| Backend      | Firebase (Firestore, Cloud Functions, FCM) 
| Auth         | Room DB + Google SSO 
| Navigation   | Compose Navigation with BottomNavGraph 
| DI           | Hilt (AppModule) 
| Database     | Room (local) + Firestore (cloud sync) 


Feature Checklist

User registration and login with encrypted password
Single Sign-On (Google SSO)
Settings screen with editable preferences
REST API integration via Firebase backend
Offline mode with RoomDB sync
Real-time push notifications using Firebase Cloud Messaging
Multi-language support (English + setswana)
Innovative features from Part 1 implemented
App icon and final image assets included


How to Run the App

Prerequisites
- Android Studio Electric Eel or newer  
- Kotlin 1.9+  
- Firebase project with Firestore enabled  
- Google Play Console account (for publishing)

Setup Instructions

1. Clone the repo:
   bash
   git clone https://github.com/your-username/GigApp.git
   

2. Open in Android Studio

3. Configure Firebase:
   - Add google-services.json to /app

4. Run the app:
   - Select emulator or device
   - Click  to build and launch


Release Notes – Version 1.0

Updates Since Prototype

| Feature                   | Description 
|                           |
|  Bottom Navigation        | Added BottomNavigationBar.kt with Home, Profile, Settings tabs 
|  Personalized Greeting    | Displays logged-in user's name on Home screen 
|  Logout Functionality     | Clears user session and navigates to login 
|  Gig Stats Card           | Shows completed gigs, applications, and earnings 
|  Navigation Architecture  | Split into NavGraph.kt and BottomNavGraph.kt for modular routing 




- ![Home Screen]  
- ![Profile Tab]
- ![Settings Tab]  
- ![Gig Stats]  
- ![Google Play Console Upload]



Video Presentation

🎥 [Watch the Demo on YouTube](https://youtu.be/your-video-id) 







