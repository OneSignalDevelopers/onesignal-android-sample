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

### Trigger In-App Message

#### 1. Create a new In-App Message

Navigate to _Messages_ and select **In-App** from the submenu...

![e3e65fc86de0dfb1d34a26a343f82416](https://github.com/OneSignalDevelopers/onesignal-android-sample/assets/1715082/d9645285-6b86-45dd-8723-8af0ad1e8c19)

Choose **New Message** button...

![901a597c23b287d4bb2f5239548e0e80](https://github.com/OneSignalDevelopers/onesignal-android-sample/assets/1715082/78583f5e-c931-427a-8667-6ad25759f00a)

Choose **New In-App** from the dropdown.

#### 2. Configure In-App Message

Name the In-App Message "Sample IAM"...

![ca0c5009187761be3f10950ddd31ca0c](https://github.com/OneSignalDevelopers/onesignal-android-sample/assets/1715082/07e4dd49-6014-48df-82ed-866949459885)

Configure the In-App Message to be presented to **All Users**...

![ef8bcd8f47903fa17f81551cbe7129c0](https://github.com/OneSignalDevelopers/onesignal-android-sample/assets/1715082/e0076b84-3e22-4fa3-8dae-9109be13a04d)

Use the block editor to create the content of your In-App Message...

![2aec0445942d753e151f455e0814c30e](https://github.com/OneSignalDevelopers/onesignal-android-sample/assets/1715082/2d7cd693-5ab0-4d55-900f-362aed9700a3)

Configure the In-App Message to trigger when a certain condition is met i.e., `show_sample_iam is 1`...

![26969694a431ea236309c9ec0dca6f4e](https://github.com/OneSignalDevelopers/onesignal-android-sample/assets/1715082/ed5ff565-a477-4e51-9961-bbad9179f4f9)

Schedule the In-App Message to be presented immediately, to show until dismissed by the user, and to present itself everytime the trigger contions is met. Then choose **Make Message Live**...

![3d409d47880a9fd0fb7b63f4f89bacfe](https://github.com/OneSignalDevelopers/onesignal-android-sample/assets/1715082/272bffb7-eef7-4028-889f-a75df398239a)

Review configuration and confirm when correct.

#### 3. Launch In-App Message

Tap the **Present In-App Message** button in the sample app to present In-App Message.

# Support

## Ask a question about OneSignal

You can ask questions about the OneSignal xxx SDK and related topics in the **onesignal-xxx-sdk** repository.

🙋‍♂️ [Ask a question](https://github.com/OneSignal/OneSignal-Android-SDK/issues/new?assignees=&labels=question&projects=&template=ask-question.yml&title=%5Bquestion%5D%3A+)

## Create a bug report

If you receive an error message or get blocked by an issue, please create a bug report!

🪳 [Create bug report](https://github.com/OneSignal/OneSignal-Android-SDK/issues/new?assignees=&labels=bug%2Ctriage&projects=&template=bug-report.yml&title=%5BBug%5D%3A+)

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
