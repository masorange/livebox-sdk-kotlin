package es.masorange.livebox.sdk.network.apis.livebox

import es.masorange.livebox.sdk.business.WeekDay
import es.masorange.livebox.sdk.business.WeekDayHour
import es.masorange.livebox.sdk.business.toSchedule
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDevice
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDeviceAddRule
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDeviceUpdateRule
import es.masorange.livebox.sdk.domain.livebox.models.Capabilities
import es.masorange.livebox.sdk.domain.livebox.models.DeviceDetail
import es.masorange.livebox.sdk.domain.livebox.models.DeviceInfo
import es.masorange.livebox.sdk.domain.livebox.models.Schedule
import es.masorange.livebox.sdk.domain.livebox.models.GeneralInfo
import es.masorange.livebox.sdk.domain.livebox.models.Wifi
import es.masorange.livebox.sdk.domain.livebox.models.AccessPoint
import es.masorange.livebox.sdk.domain.livebox.models.DeviceAlias
import es.masorange.livebox.sdk.domain.livebox.models.Enabled
import es.masorange.livebox.sdk.domain.livebox.models.Feature
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Id.CONNECTED_DEVICES
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Id.CONNECTED_DEVICES_MAC
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Id.GENERAL_INFO
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Id.PC_DEVICES
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Id.PC_DEVICES_MAC
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Id.PC_DEVICES_MAC_SCHEDULES
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Id.WIFI
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Id.WLAN_ACCESS_POINT
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Id.WLAN_INTERFACE
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Ops.ADD
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Ops.DELETE
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Ops.READ
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Ops.WRITE
import es.masorange.livebox.sdk.domain.livebox.models.WlanInterface
import es.masorange.livebox.sdk.utils.withDegradedServer

class LiveboxApiFake : LiveboxApi {
    private val schedule = listOf(
        WeekDayHour(WeekDay.MONDAY, 1).toSchedule(),
        WeekDayHour(WeekDay.TUESDAY, 1).toSchedule(),
        WeekDayHour(WeekDay.WEDNESDAY, 1).toSchedule(),
        WeekDayHour(WeekDay.THURSDAY, 1).toSchedule(),
        WeekDayHour(WeekDay.FRIDAY, 1).toSchedule()
    )
    private val blockedDevice = BlockedDevice(
        mac = "11:22:33:44:55:66",
        mode = BlockedDevice.Mode.SCHEDULE,
        name = "iFlary 16",
        status = BlockedDevice.Status.ENABLED,
        schedule = schedule
    )

    override suspend fun getCapabilities() = withDegradedServer {
        Capabilities(
            features = listOf(
                Feature(GENERAL_INFO, "API/GeneralInfo", listOf(READ)),
                Feature(CONNECTED_DEVICES, "/API/LAN/DHCP/Connected", listOf(READ)),
                Feature(CONNECTED_DEVICES_MAC, "/API/LAN/DHCP/Connected/{mac}", listOf(READ, WRITE)),
                Feature(WIFI, "/API/LAN/WIFI", listOf(READ)),
                Feature(WLAN_INTERFACE, "/API/LAN/WIFI/{wlan_ifc}", listOf(READ, WRITE)),
                Feature(WLAN_ACCESS_POINT, "/API/LAN/WIFI/{wlan_ifc}/{wlan_ap}", listOf(READ, WRITE)),
                Feature(PC_DEVICES, "/API/Services/ParentalCtrl/Devices", listOf(READ, ADD)),
                Feature(PC_DEVICES_MAC, "/API/Services/ParentalCtrl/Devices/{mac}", listOf(READ, WRITE, DELETE)),
                Feature(PC_DEVICES_MAC_SCHEDULES, "/API/Services/ParentalCtrl/Devices/{mac}/Schedules", listOf(READ, ADD, DELETE))
            )
        )
    }

    override suspend fun getGeneralInfo(uri: String) = withDegradedServer {
        GeneralInfo(
            manufacturer = "Vapy Networks",
            manufacturerOUI = "FLS123",
            modelName = "Vapybox 3000",
            description = "Vapybox 3000",
            productClass = "Vapybox 3000",
            serialNumber = "123456789",
            hardwareVersion = "1.0.0",
            softwareVersion = "1.0.0",
            rescueVersion = "1.0.0",
            modernFirmwareVersion = "1.0.0",
            enabledOptions = "",
            additionalHardwareVersion = "1.0.0",
            additionalSoftwareVersion = "1.0.0",
            specVersion = "1.0.0",
            provisionCode = "FLS123",
            upTime = 123456L,
            firstUseDate = "2025-06-25T00:00:00",
            deviceLog = "",
            vendorConfigFileNumberOfEntries = "",
            manufacturerURL = "https://vapynetworks.com",
            country = "Japan",
            numberOfReboots = 0,
            upgradeOccurred = false,
            resetOccurred = false,
            restoreOccurred = false,
            apiVersion = "1.0.0",
            routerImage = "http://vapybox.png",
            routerName = "Vapybox 3000"
        )
    }

    override suspend fun getConnectedDevices(uri: String) = withDegradedServer {
        listOf(
            DeviceInfo(
                physAddress = "00:11:22:33:44:55",
                ipAddress = "192.168.1.2",
                hostName = "Vapyphone Pro",
                interfaceType = DeviceInfo.InterfaceType.WIFI,
                active = true,
                alias = "Vapy",
                ipV6Address = "fe80::1"
            ),
            DeviceInfo(
                physAddress = "66:77:88:99:AA:BB",
                ipAddress = "192.168.1.3",
                hostName = "Jolt Laptop",
                interfaceType = DeviceInfo.InterfaceType.ETHERNET,
                active = true,
                alias = "My PC",
                ipV6Address = "fe80::2"
            )
        )
    }

    override suspend fun getConnectedDevicesMac(uri: String) = withDegradedServer {
        DeviceDetail(
            physAddress = "00:11:22:33:44:55",
            ipAddress = "192.168.1.2",
            hostName = "Vapyphone Pro",
            interfaceType = DeviceDetail.InterfaceType.WIFI,
            active = true,
            alias = "Vapy",
            ipV6Address = "fe80::1",
            lastConnection = "2025-06-25T12:00:00",
            tags = "",
            layer2Interface = "w10",
            deviceType = "Smartphone"
        )
    }

    override suspend fun putConnectedDevicesMac(uri: String, alias: DeviceAlias) = withDegradedServer {
        DeviceDetail(
            physAddress = "00:11:22:33:44:55",
            ipAddress = "192.168.1.2",
            hostName = alias.alias,
            interfaceType = DeviceDetail.InterfaceType.WIFI,
            active = true,
            alias = alias.alias,
            ipV6Address = "fe80::1",
            lastConnection = "2025-06-25T12:00:00",
            tags = "",
            layer2Interface = "w10",
            deviceType = "Smartphone"
        )
    }

    override suspend fun getWifi(uri: String) = withDegradedServer {
        listOf(
            Wifi(
                wifiStatusButton = true
            ),
            Wifi(
                id = "24GHz",
                status = Wifi.Status.UP,
                frequency = Wifi.Frequency._24GHZ
            ),
            Wifi(
                id = "5GHz",
                status = Wifi.Status.UP,
                frequency = Wifi.Frequency._5GHZ
            )
        )
    }

    override suspend fun getWlanInterface(uri: String) = withDegradedServer {
        WlanInterface(
            id = "24GHz",
            status = WlanInterface.Status.UP,
            frequency = WlanInterface.Frequency._24GHZ,
            accessPoints = listOf(
                WlanInterface.ShortAccessPoint(
                    idx = "001122334455",
                    bssid = "00:11:22:33:44:55",
                    status = WlanInterface.Status.UP,
                    ssid = "Vapybox-3000-WiFi"
                )
            )
        )
    }

    override suspend fun getWlanAccessPoint(uri: String) = withDegradedServer {
        AccessPoint(
            bssid = "00:11:22:33:44:55",
            ssid = "Vapybox-3000-WiFi",
            type = AccessPoint.Type.HOME,
            manner = AccessPoint.Manner.COMBINED,
            status = AccessPoint.Status.UP,
            password = "password123",
            channelConf = AccessPoint.ChannelConf.AUTO,
            channel = 6,
            bandwidthConf = AccessPoint.BandwidthConf._20MHZ,
            bandwidth = "20MHz",
            schedulingAllowed = true
        )
    }

    override suspend fun putWlanAccessPoint(uri: String, accessPoint: AccessPoint) = withDegradedServer {
        accessPoint
    }

    override suspend fun getBlockedDevicesList(uri: String) = withDegradedServer {
        listOf(blockedDevice)
    }

    override suspend fun postBlockedDevicesToList(uri: String, blockedDeviceAddRule: BlockedDeviceAddRule) = withDegradedServer {
        blockedDevice.copy(status = blockedDeviceAddRule.status)
    }

    override suspend fun getBlockedDevice(uri: String) = withDegradedServer {
        blockedDevice
    }

    override suspend fun putBlockedDevice(uri: String, blockedDeviceUpdateRule: BlockedDeviceUpdateRule) = withDegradedServer {
        blockedDevice.copy(
            mode = blockedDeviceUpdateRule.mode,
            status = blockedDeviceUpdateRule.status
        )
    }

    override suspend fun getDevicesMacSchedules(uri: String) = withDegradedServer {
        blockedDevice.schedule!!
    }

    override suspend fun postDevicesMacSchedules(uri: String, scheduleList: List<Schedule>) = withDegradedServer {
        scheduleList
    }

    override suspend fun getWlanSchedule(uri: String) = withDegradedServer {
        schedule
    }

    override suspend fun postWlanSchedule(uri: String, scheduleList: List<Schedule>) = withDegradedServer {
        scheduleList
    }

    override suspend fun deleteWlanSchedule(uri: String, scheduleList: List<Schedule>) = withDegradedServer {
        emptyList<Schedule>()
    }

    override suspend fun getWlanScheduleEnabled(uri: String) = withDegradedServer {
        Enabled(enabled = false)
    }

    override suspend fun putWlanScheduleEnabled(uri: String, enabled: Enabled) = withDegradedServer {
        enabled
    }
}