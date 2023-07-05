## How to Add Push Notifications to an Android App with Java and Kotlin

This is a OneSignal to Android SDK sample .

## Requirements

  * Android Studio Chipmunk.
  * OneSignal account and project.
  * Firebase Server Key.
  * Physical or virtual device.

## Usage
To test this example, clone this repository as follows:
>
>     $ git clone https://github.com/OneSignalDevelopers/OneSignal-Android-Push-Sample

In Android Studio:

1. File --> Open
2. Choose the path where you cloned the project, select and OK.
3. Click the green play icon to run the app.


![OneSignal](https://github.com/OneSignalDevelopers/.github/blob/main/assets/onesignal-banner.png?raw=true)

<div align="center">
  <a href="https://documentation.onesignal.com/docs/onboarding-with-onesignal" target="_blank">Quickstart</a>
  <span>&nbsp;&nbsp;•&nbsp;&nbsp;</span>
  <a href="https://onesignal.com/" target="_blank">Website</a>
  <span>&nbsp;&nbsp;•&nbsp;&nbsp;</span>
  <a href="https://documentation.onesignal.com/docs" target="_blank">Docs</a>
  <span>&nbsp;&nbsp;•&nbsp;&nbsp;</span>
  <a href="https://github.com/OneSignalDevelopers" target="_blank">Examples</a>
  <br />
  <hr />
</div>

# OneSignal Android Sample

OneSignal makes engaging customers simple and is the fastest, most reliable service to send push notifications, in-app messages, SMS, and emails.

This project demonstrates sending push notifications using **OneSignal Android SDK**. You can use this project as a boilerplate or reference to start your project.

## 🚦 Getting started

This project assumes that you already have a OneSignal app created with push notifications setup. If you don't yet have a OneSignal app, [create one](https://documentation.onesignal.com/docs/apps-organizations#create-an-app) first, then follow the steps below to integrate the OneSignal SDK into your [Android](https://documentation.onesignal.com/docs/android-sdk-setup) or [iOS](https://documentation.onesignal.com/docs/ios-sdk-setup) app.

### Initialize OneSignal 

https://github.com/OneSignalDevelopers/onesignal-android-sample/blob/3e5493fe01b67eb294ff071d695c081479d0c895/app/src/main/java/com/onesignal/sample/android/MainApplication.kt#L8-L14

### Login user

https://github.com/OneSignalDevelopers/onesignal-android-sample/blob/3e5493fe01b67eb294ff071d695c081479d0c895/app/src/main/java/com/onesignal/sample/android/MainActivity.kt#L13C8-L15C10

### Enable push subscription

https://github.com/OneSignalDevelopers/onesignal-android-sample/blob/3e5493fe01b67eb294ff071d695c081479d0c895/app/src/main/java/com/onesignal/sample/android/MainActivity.kt#L21-L23

### Send push notification

```bash
## Send notification
# https://documentation.onesignal.com/reference/create-notification
curl -X "POST" "http://notifications" \
     -H 'Content-Type: application/json' \
     -u '<Your REST API Key>:' \
     -d $'{
  "headings": {
    "en": "🥳🍾🎊"
  },
  "included_segments": [
    "Subscribed Users"
  ],
  "app_id": "<Your APP ID>",
  "contents": {
    "en": "You recieved your first push!"
  },
  "url": "https://onesignal.com"
}'
```

# Support

## Ask a question about OneSignal

You can ask questions about the OneSignal xxx SDK and related topics in the **onesignal-xxx-sdk** repository.

🙋‍♂️ [Ask a question](#)

[☝️ Update link to SDK repo issue template]

## Create a bug report

If you receive an error message or get blocked by an issue, please create a bug report!

🪳 [Create bug report](#)

[☝️ Update link to SDK repo issue template]

# ❤️ Developer Community

For additional resources, please join the [OneSignal Developer Community](https://onesignal.com/onesignal-developers).

Get in touch with us or learn more about OneSignal through the channels below.

- [Follow us on Twitter](https://twitter.com/onesignaldevs) to never miss any updates from the OneSignal team, ecosystem & community
- [Join us on Discord](https://discord.gg/EP7gf6Uz7G) to be a part of the OneSignal Developers community, showcase your work and connect with other OneSignal developers
- [Read the OneSignal Blog](https://onesignal.com/blog/) for the latest announcements, tutorials, in-depth articles & more.
- [Subscribe to us on YouTube](https://www.youtube.com/channel/UCe63d5EDQsSkOov-bIE_8Aw/featured) for walkthroughs, courses, talks, workshops & more.
- [Follow us on Twitch](https://www.twitch.tv/onesignaldevelopers) for live streams, office hours, support & more.

## Show your support

Give a ⭐️ if this project helped you, and watch this repo to stay up to date.
