# Module livebox-sdk

Configure and monitor Livebox and compatible routers through their local REST API.

The SDK lets you read router information, manage Wi-Fi interfaces and access points, list connected devices and
configure Wi-Fi and parental control schedules.

Every operation is backed by a router *feature*. The router publishes the features it supports through its
capabilities endpoint, so call `login` (which fetches them) before using any other method.

```kotlin
val liveboxSdk = LiveboxSdkBuilder()
    .withContext(context)
    .withEnvironment(Environment.LIVE)
    .build()

lifecycleScope.launch {
    if (liveboxSdk.login(password = "admin-password")) {
        val info = liveboxSdk.getGeneralInfo()
        Log.d("Livebox", "Router model: ${info.modelName}")
    }
}
```

All methods are `suspend` functions and throw `retrofit2.HttpException` when the router responds with an HTTP error.

## Network requirements

The router is reached through the default gateway of the current Wi-Fi network, using plain HTTP:

- `build()` throws `NoWifiException` with `Environment.LIVE` if the device is not connected to a Wi-Fi network.
- Your app must allow cleartext traffic with a network security configuration.
- On Android 17 (API 37) and higher, your app must request the `ACCESS_LOCAL_NETWORK` runtime permission before
  building the SDK. The permission is already declared in the SDK manifest.

See the [README](https://github.com/masorange/livebox-sdk-kotlin#readme) for the full setup.

## Authentication

The router API has no sessions. Credentials are sent with every request using HTTP Basic Authentication.
`login` stores and validates them, returning `false` and clearing them if the router rejects them.
The username defaults to `"UsrAdmin"`.

## Schedules

Wi-Fi and parental control schedules are 1-hour slots of the week during which there is no access to the network.
The SDK represents them as `WeekDayHour` values:

```kotlin
// No Wi-Fi on Mondays from 23:00 to 00:00
liveboxSdk.postWlanSchedule(
    wlanIfc = "24GHz",
    wlanAp = accessPoint.idx,
    scheduleList = listOf(WeekDayHour(WeekDay.MONDAY, 23))
)
```

# Package es.masorange.livebox.sdk

The SDK entry point: build a `LiveboxSdk` with `LiveboxSdkBuilder`.

# Package es.masorange.livebox.sdk.business

Helpers to work with schedules as days and hours of the week.

# Package es.masorange.livebox.sdk.domain.livebox.models

The models sent to and received from the router.

# Package es.masorange.livebox.sdk.exceptions

Exceptions thrown by the SDK.

# Package es.masorange.livebox.sdk.network

The environments the SDK can connect to.
