package es.masorange.livebox.sdk.network.apis.livebox

import es.masorange.livebox.sdk.domain.livebox.models.BlockedDevice
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDeviceAddRule
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDeviceUpdateRule
import es.masorange.livebox.sdk.domain.livebox.models.Capabilities
import es.masorange.livebox.sdk.domain.livebox.models.DeviceDetail
import es.masorange.livebox.sdk.domain.livebox.models.DeviceInfo
import es.masorange.livebox.sdk.domain.livebox.models.DeviceSchedule
import es.masorange.livebox.sdk.domain.livebox.models.GeneralInfo
import es.masorange.livebox.sdk.domain.livebox.models.Wifi
import es.masorange.livebox.sdk.domain.livebox.models.AccessPoint
import es.masorange.livebox.sdk.domain.livebox.models.DeviceAlias
import es.masorange.livebox.sdk.domain.livebox.models.WlanInterface
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface LiveboxApi {
    @GET("API/Capabilities")
    suspend fun getCapabilities(): Capabilities

    @GET
    suspend fun getGeneralInfo(
        @retrofit2.http.Url uri: String
    ): GeneralInfo

    @GET
    suspend fun getConnectedDevices(
        @retrofit2.http.Url uri: String
    ): List<DeviceInfo>

    @GET
    suspend fun getConnectedDevicesMac(
        @retrofit2.http.Url uri: String
    ): DeviceDetail

    @PUT
    suspend fun putConnectedDevicesMac(
        @retrofit2.http.Url uri: String,
        @Body alias: DeviceAlias
    ): DeviceDetail

    @GET
    suspend fun getWifi(
        @retrofit2.http.Url uri: String
    ): List<Wifi>

    @GET
    suspend fun getWlanInterface(
        @retrofit2.http.Url uri: String
    ): WlanInterface

    @GET
    suspend fun getWlanAccessPoint(
        @retrofit2.http.Url uri: String
    ): AccessPoint

    @PUT
    suspend fun putWlanAccessPoint(
        @retrofit2.http.Url uri: String,
        @Body accessPoint: AccessPoint
    ): AccessPoint

    @GET
    suspend fun getBlockedDevicesList(
        @retrofit2.http.Url uri: String
    ): List<BlockedDevice>

    @POST
    suspend fun postBlockedDevicesToList(
        @retrofit2.http.Url uri: String,
        @Body blockedDeviceAddRule: BlockedDeviceAddRule
    ): BlockedDevice

    @GET
    suspend fun getBlockedDevice(
        @retrofit2.http.Url uri: String
    ): BlockedDevice

    @PUT
    suspend fun putBlockedDevice(
        @retrofit2.http.Url uri: String,
        @Body blockedDeviceUpdateRule: BlockedDeviceUpdateRule
    ): BlockedDevice

    @GET
    suspend fun getDevicesMacSchedules(
        @retrofit2.http.Url uri: String
    ): List<DeviceSchedule>

    @POST
    suspend fun postDevicesMacSchedules(
        @retrofit2.http.Url uri: String,
        @Body deviceScheduleList: List<DeviceSchedule>
    ): List<DeviceSchedule>
}