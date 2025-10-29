package es.masorange.livebox.sdk

import android.content.Context
import es.masorange.livebox.sdk.business.WeekDayHour
import es.masorange.livebox.sdk.business.toSchedule
import es.masorange.livebox.sdk.business.toWeekDayHour
import es.masorange.livebox.sdk.di.LibDI
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDeviceAddRule
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDeviceUpdateRule
import es.masorange.livebox.sdk.domain.livebox.models.Capabilities
import es.masorange.livebox.sdk.domain.livebox.models.Schedule
import es.masorange.livebox.sdk.domain.livebox.models.Feature.Id.*
import es.masorange.livebox.sdk.domain.livebox.models.AccessPoint
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDevice
import es.masorange.livebox.sdk.domain.livebox.models.DeviceAlias
import es.masorange.livebox.sdk.domain.livebox.models.Enabled
import es.masorange.livebox.sdk.network.Environment
import es.masorange.livebox.sdk.network.apis.ApiProvider
import es.masorange.livebox.sdk.network.apis.livebox.LiveboxApi
import es.masorange.livebox.sdk.network.auth.AuthManager
import es.masorange.livebox.sdk.utils.addParams
import es.masorange.livebox.sdk.utils.toUriMac
import org.kodein.di.direct
import org.kodein.di.instance
import retrofit2.HttpException

/**
 * SDK for interacting with the Livebox API.
 * Provides methods to manage devices, Wi-Fi settings, access points, and device schedules.
 *
 * @param context Context where the init is performed.
 * @param environment The environment to use (e.g., TEST or LIVE).
 */
class LiveboxSdk internal constructor(
    private val context: Context,
    private val environment: Environment
) {
    private lateinit var liveboxApi: LiveboxApi
    private lateinit var capabilities: Capabilities

    private val URI_MAC = "mac"
    private val URI_WLAN_IFC = "wlan_ifc"
    private val URI_WLAN_AP = "wlan_ap"

    /**
     * Initializes the SDK with the given context and environment.
     */
    internal fun init() {
        LibDI.initializeInjection(context, environment)

        if (environment == Environment.LIVE) {
            // Verify we are connected to a Wi-Fi and set up the router's IP
            Environment.setupGateway(context)
        }

        liveboxApi = when (environment) {
            Environment.TEST -> LibDI.di.direct.instance<ApiProvider<LiveboxApi>>().testInstance
            Environment.LIVE -> LibDI.di.direct.instance<ApiProvider<LiveboxApi>>().liveInstance
        }
    }

    /**
     * Logs in to the Livebox API using the provided credentials.
     *
     * @param username Optional username.
     * @param password The password for login to the Router's settings, AKA the password printed in the Router's label.
     * @return True if login is successful, false otherwise.
     * @throws HttpException If an HTTP error occurs.
     */
    @Throws(HttpException::class)
    suspend fun login(username: String? = null,
                      password: String): Boolean {
        return try {
            username?.let {
                AuthManager.setCredentials(username = it, password = password)
            } ?: AuthManager.setCredentials(password = password)

            getCapabilities()
            true
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthManager.clearCredentials()
                false
            } else {
                throw e
            }
        }
    }

    /**
     * Retrieves the Livebox API capabilities.
     *
     * @return The [Capabilities] object.
     */
    suspend fun getCapabilities(): Capabilities {
        if (::capabilities.isInitialized) {
            return capabilities
        }

        capabilities = liveboxApi.getCapabilities()
        return capabilities
    }

    /**
     * Gets general information from the Livebox API.
     *
     * @return General information.
     */
    suspend fun getGeneralInfo() = liveboxApi.getGeneralInfo(
        uri = capabilities.featuresMap[GENERAL_INFO]!!.uri
    )

    /**
     * Gets the list of connected devices.
     *
     * @return List of connected devices.
     */
    suspend fun getConnectedDevices() = liveboxApi.getConnectedDevices(
        uri = capabilities.featuresMap[CONNECTED_DEVICES]!!.uri
    )

    /**
     * Gets detailed information about a specific device.
     *
     * @param mac The MAC address of the device.
     * @return Device details.
     */
    suspend fun getDeviceDetail(mac: String) = liveboxApi.getConnectedDevicesMac(
        uri = capabilities.featuresMap[CONNECTED_DEVICES_MAC]!!.uri
            .addParams(mapOf(URI_MAC to mac.toUriMac()))
    )

    /**
     * Sets an alias for a specific device.
     *
     * @param mac The MAC address of the device.
     * @param alias The alias to set.
     */
    suspend fun setDeviceAlias(mac: String,
                               alias: String) = liveboxApi.putConnectedDevicesMac(
        uri = capabilities.featuresMap[CONNECTED_DEVICES_MAC]!!.uri
            .addParams(mapOf(URI_MAC to mac.toUriMac())),
        alias = DeviceAlias(alias = alias)
    )

    /**
     * Gets the list of Wi-Fi interfaces.
     *
     * @return List of Wi-Fi interfaces.
     */
    suspend fun getWifiInterfaces() = liveboxApi.getWifi(
        uri = capabilities.featuresMap[WIFI]!!.uri
    )

    /**
     * Gets information about a specific WLAN interface.
     *
     * @param wlanIfc The WLAN interface identifier.
     * @return WLAN interface details.
     */
    suspend fun getWlanInterface(wlanIfc: String) = liveboxApi.getWlanInterface(
        uri = capabilities.featuresMap[WLAN_INTERFACE]!!.uri
            .addParams(mapOf(URI_WLAN_IFC to wlanIfc))
    )

    /**
     * Gets information about a specific access point.
     *
     * @param wlanIfc The WLAN interface identifier.
     * @param wlanAp The access point identifier.
     * @return Access point details.
     */
    suspend fun getAccessPoint(wlanIfc: String,
                               wlanAp: String) = liveboxApi.getWlanAccessPoint(
        uri = capabilities.featuresMap[WLAN_ACCESS_POINT]!!.uri
            .addParams(mapOf(
                URI_WLAN_IFC to wlanIfc,
                URI_WLAN_AP to wlanAp))
    )

    /**
     * Updates the configuration of a specific access point (e.g. Password changing).
     *
     * @param wlanIfc The WLAN interface identifier.
     * @param wlanAp The access point identifier.
     * @param accessPoint The new access point configuration.
     */
    suspend fun updateAccessPoint(wlanIfc: String,
                                  wlanAp: String,
                                  accessPoint: AccessPoint) = liveboxApi.putWlanAccessPoint(
        uri = capabilities.featuresMap[WLAN_ACCESS_POINT]!!.uri
            .addParams(mapOf(
                URI_WLAN_IFC to wlanIfc,
                URI_WLAN_AP to wlanAp)),
        accessPoint = accessPoint
    )

    /**
     * Gets the list of devices with blocking conditions.
     *
     * @return List of devices.
     */
    suspend fun getBlockedDevices() = liveboxApi.getBlockedDevicesList(
        uri = capabilities.featuresMap[PC_DEVICES]!!.uri
    )

    /**
     * Gets information about a specific device with blocking conditions.
     *
     * @param mac The MAC address of the device.
     * @return Blocked device details.
     */
    suspend fun getBlockedDevice(mac: String) = liveboxApi.getBlockedDevice(
        uri = capabilities.featuresMap[PC_DEVICES_MAC]!!.uri
            .addParams(mapOf(URI_MAC to mac.toUriMac()))
    )

    /**
     * Gets the schedules for a specific device.
     *
     * @param mac The MAC address of the device.
     * @return List of schedules.
     */
    suspend fun getDeviceSchedules(mac: String) = liveboxApi.getDevicesMacSchedules(
        uri = capabilities.featuresMap[PC_DEVICES_MAC_SCHEDULES]!!.uri
            .addParams(mapOf(URI_MAC to mac.toUriMac()))
    )

    /**
     * Sets schedules for a specific device.
     *
     * @param mac The MAC address of the device.
     * @param schedules The list of schedules to set.
     * @return The updated list of schedules.
     */
    suspend fun setDeviceSchedules(mac: String,
                                   schedules: List<WeekDayHour>): List<Schedule> {
        val deviceSchedules = getBlockedDevices().find { it.mac == mac }

        // Check before posting a device to the devices blocked list, as duplicates will be created
        if (deviceSchedules == null) {
            postBlockedDevicesToList(
                BlockedDeviceAddRule(
                    mac = mac,
                    status = BlockedDevice.Status.ENABLED
                )
            )
        }

        // Put the blocked device rule to enabled with schedule
        putBlockedDevice(
            mac = mac.toUriMac(),
            blockedDeviceUpdateRule = BlockedDeviceUpdateRule(
                mode = BlockedDevice.Mode.SCHEDULE,
                status = BlockedDevice.Status.ENABLED
            )
        )

        // Post the device's schedules
        return postDeviceSchedules(
            mac = mac.toUriMac(),
            scheduleList = schedules.map { it.toSchedule() }
        )
    }

    private suspend fun postBlockedDevicesToList(blockedDeviceAddRule: BlockedDeviceAddRule) = liveboxApi.postBlockedDevicesToList(
        uri = capabilities.featuresMap[PC_DEVICES]!!.uri,
        blockedDeviceAddRule = blockedDeviceAddRule
    )

    private suspend fun putBlockedDevice(mac: String,
                                         blockedDeviceUpdateRule: BlockedDeviceUpdateRule) = liveboxApi.putBlockedDevice(
        uri = capabilities.featuresMap[PC_DEVICES_MAC]!!.uri
            .addParams(mapOf(URI_MAC to mac.toUriMac())),
        blockedDeviceUpdateRule = blockedDeviceUpdateRule
    )

    private suspend fun postDeviceSchedules(mac: String,
                                            scheduleList: List<Schedule>) = liveboxApi.postDevicesMacSchedules(
        uri = capabilities.featuresMap[PC_DEVICES_MAC_SCHEDULES]!!.uri
            .addParams(mapOf(URI_MAC to mac.toUriMac())),
        scheduleList = scheduleList
    )

    /**
     * Gets schedules for a specific WLAN network.
     *
     * @param wlanIfc The WLAN interface identifier.
     * @param wlanAp The access point identifier.
     * @return The list of schedules for the network.
     */
    suspend fun getWlanSchedule(wlanIfc: String,
                                wlanAp: String) = liveboxApi.getWlanSchedule(
        uri = capabilities.featuresMap[WLAN_SCHEDULE]!!.uri
            .addParams(mapOf(
                URI_WLAN_IFC to wlanIfc,
                URI_WLAN_AP to wlanAp))
    ).map { it.toWeekDayHour() }

    /**
     * Sets up a new list of schedules for a specific WLAN network.
     * If schedules already exist, they will be combined with the new ones.
     *
     * @param wlanIfc The WLAN interface identifier.
     * @param wlanAp The access point identifier.
     * @param scheduleList The list of schedules to configure.
     * @return The complete list of schedules for the network.
     */
    suspend fun postWlanSchedule(wlanIfc: String,
                                 wlanAp: String,
                                 scheduleList: List<WeekDayHour>) = liveboxApi.postWlanSchedule(
        uri = capabilities.featuresMap[WLAN_SCHEDULE]!!.uri
            .addParams(mapOf(
                URI_WLAN_IFC to wlanIfc,
                URI_WLAN_AP to wlanAp)),
        scheduleList = scheduleList.map { it.toSchedule() }
    ).map { it.toWeekDayHour() }

    /**
     * Deletes schedules for a specific WLAN network.
     *
     * @param wlanIfc The WLAN interface identifier.
     * @param wlanAp The access point identifier.
     * @param scheduleList The list of schedules to delete.
     * @return The resulting list of schedules for the network.
     */
    suspend fun deleteWlanSchedule(wlanIfc: String,
                                   wlanAp: String,
                                   scheduleList: List<WeekDayHour>) = liveboxApi.deleteWlanSchedule(
        uri = capabilities.featuresMap[WLAN_SCHEDULE]!!.uri
            .addParams(mapOf(
                URI_WLAN_IFC to wlanIfc,
                URI_WLAN_AP to wlanAp)),
        scheduleList = scheduleList.map { it.toSchedule() }
    ).map { it.toWeekDayHour() }

    /**
     * Gets if schedules are enabled for a specific WLAN network.
     *
     * @param wlanIfc The WLAN interface identifier.
     * @param wlanAp The access point identifier.
     * @return [Enabled] containing the enabled status of the network.
     */
    suspend fun getWlanScheduleEnabled(wlanIfc: String,
                                       wlanAp: String) = liveboxApi.getWlanScheduleEnabled(
        uri = capabilities.featuresMap[WLAN_SCHEDULE_ENABLE]!!.uri
            .addParams(mapOf(
                URI_WLAN_IFC to wlanIfc,
                URI_WLAN_AP to wlanAp))
    )

    /**
     * Updates the enabled schedules status for a specific WLAN network.
     *
     * @param wlanIfc The WLAN interface identifier.
     * @param wlanAp The access point identifier.
     * @param enabled [Enabled] containing the new enabled status for the network.
     * @return [Enabled] containing the enabled status of the network.
     */
    suspend fun putWlanScheduleEnabled(wlanIfc: String,
                                       wlanAp: String,
                                       enabled: Enabled) = liveboxApi.putWlanScheduleEnabled(
        uri = capabilities.featuresMap[WLAN_SCHEDULE_ENABLE]!!.uri
            .addParams(mapOf(
                URI_WLAN_IFC to wlanIfc,
                URI_WLAN_AP to wlanAp)),
        enabled = enabled
    )
}