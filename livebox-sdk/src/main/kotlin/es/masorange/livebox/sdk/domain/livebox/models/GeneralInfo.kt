package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * Represents general information about the router.
 *
 * @param manufacturer The name of the router's manufacturer.
 * @param manufacturerOUI The Organizationally Unique Identifier (OUI) of the manufacturer.
 * @param modelName The model name of the router.
 * @param description A description of the router.
 * @param productClass The product class of the router.
 * @param serialNumber The serial number of the router.
 * @param hardwareVersion The hardware version of the router.
 * @param softwareVersion The software version of the router.
 * @param rescueVersion The rescue version of the router's firmware.
 * @param modernFirmwareVersion The version of the modern firmware installed on the router.
 * @param enabledOptions A string representing the enabled options on the router.
 * @param additionalHardwareVersion Additional hardware version information.
 * @param additionalSoftwareVersion Additional software version information.
 * @param specVersion The specification version of the router.
 * @param provisionCode The provision code of the router.
 * @param upTime The uptime of the router in seconds.
 * @param firstUseDate The date when the router was first used.
 * @param deviceLog The device log of the router.
 * @param vendorConfigFileNumberOfEntries The number of entries in the vendor configuration file.
 * @param manufacturerURL The URL of the manufacturer's website.
 * @param country The country where the router is used.
 * @param numberOfReboots The number of times the router has been rebooted.
 * @param upgradeOccurred Indicates whether a firmware upgrade has occurred.
 * @param resetOccurred Indicates whether a reset has occurred.
 * @param restoreOccurred Indicates whether a restore operation has occurred.
 * @param apiVersion The API version supported by the router.
 * @param routerImage The image or icon representing the router.
 * @param routerName The name of the router.
 */
data class GeneralInfo(
    @Json(name = "Manufacturer") val manufacturer: String,
    @Json(name = "ManufacturerOUI") val manufacturerOUI: String? = null,
    @Json(name = "ModelName") val modelName: String,
    @Json(name = "Description") val description: String? = null,
    @Json(name = "ProductClass") val productClass: String,
    @Json(name = "SerialNumber") val serialNumber: String,
    @Json(name = "HardwareVersion") val hardwareVersion: String,
    @Json(name = "SoftwareVersion") val softwareVersion: String,
    @Json(name = "RescueVersion") val rescueVersion: String? = null,
    @Json(name = "ModernFirmwareVersion") val modernFirmwareVersion: String? = null,
    @Json(name = "EnabledOptions") val enabledOptions: String? = null,
    @Json(name = "AdditionalHardwareVersion") val additionalHardwareVersion: String? = null,
    @Json(name = "AdditionalSoftwareVersion") val additionalSoftwareVersion: String? = null,
    @Json(name = "SpecVersion") val specVersion: String? = null,
    @Json(name = "ProvisionCode") val provisionCode: String? = null,
    @Json(name = "UpTime") val upTime: Long? = null,
    @Json(name = "FirstUseDate") val firstUseDate: String? = null,
    @Json(name = "DeviceLog") val deviceLog: String? = null,
    @Json(name = "VendorConfigFileNumberOfEntries") val vendorConfigFileNumberOfEntries: String? = null,
    @Json(name = "ManufacturerURL") val manufacturerURL: String? = null,
    @Json(name = "Country") val country: String? = null,
    @Json(name = "NumberOfReboots") val numberOfReboots: Int? = null,
    @Json(name = "UpgradeOccurred") val upgradeOccurred: Boolean? = null,
    @Json(name = "ResetOccurred") val resetOccurred: Boolean? = null,
    @Json(name = "RestoreOccurred") val restoreOccurred: Boolean? = null,
    @Json(name = "ApiVersion") val apiVersion: String? = null,
    @Json(name = "RouterImage") val routerImage: String? = null,
    @Json(name = "RouterName") val routerName: String? = null
)
