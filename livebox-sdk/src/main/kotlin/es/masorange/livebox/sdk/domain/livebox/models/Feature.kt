package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * Each feature the router can perform.
 *
 * @param id The feature's ID.
 * @param uri The URI to which the feature is mapped in the router.
 * @param ops The feature's available operations:
 *      "R" :: [READ]: Can be read.
 *      "W" :: [WRITE]: Can be modified.
 *      "I" :: [INVOKE]: Can be invoked as action.
 *      "A" :: [ADD]: New child resources can be added.
 *      "D" :: [DELETE]: Can be deleted.
 *      "Q" :: [QUERY]: Description can be queried for reading.
 */
data class Feature(
    @Json(name = "Id") val id: Id,
    @Json(name = "Uri") val uri: String,
    @Json(name = "Ops") val ops: List<Ops>
) {
    enum class Id(value: String) {
        @Json(name = "Capabilities") CAPABILITIES("Capabilities"),
        @Json(name = "Access") ACCESS("Access"),
        @Json(name = "GeneralInfo") GENERAL_INFO("GeneralInfo"),
        @Json(name = "EthPortId") ETH_PORT_ID("EthPortId"),
        @Json(name = "EthPort") ETH_PORT("EthPort"),
        @Json(name = "Connectivity") CONNECTIVITY("Connectivity"),
        @Json(name = "UsbPortId") USB_PORT_ID("UsbPortId"),
        @Json(name = "UsbPort") USB_PORT("UsbPort"),
        @Json(name = "FxsPort") FXS_PORT("FxsPort"),
        @Json(name = "UsbPortIdEject") USB_PORT_ID_EJECT("UsbPortIdEject"),
        @Json(name = "SipSuscription") SIP_SUBSCRIPTION("SipSuscription"),
        @Json(name = "SipLinesLine") SIP_LINES_LINE("SipLinesLine"),
        @Json(name = "SipLines") SIP_LINES("SipLines"),
        @Json(name = "H323LinesLine") H323_LINES_LINE("H323LinesLine"),
        @Json(name = "H323Lines") H323_LINES("H323Lines"),
        @Json(name = "H323") H323("H323"),
        @Json(name = "Autodial") AUTODIAL("Autodial"),
        @Json(name = "VoIP") VOIP("VoIP"),
        @Json(name = "DeviceList") DEVICE_LIST("DeviceList"),
        @Json(name = "Wifi") WIFI("Wifi"),
        @Json(name = "ConnectedDevicesMac") CONNECTED_DEVICES_MAC("ConnectedDevicesMac"),
        @Json(name = "ConnectedDevices") CONNECTED_DEVICES("ConnectedDevices"),
        @Json(name = "LanDhcp") LAN_DHCP("LanDhcp"),
        @Json(name = "Autoreboot") AUTOREBOOT("Autoreboot"),
        @Json(name = "Reboot") REBOOT("Reboot"),
        @Json(name = "FullReset") FULL_RESET("FullReset"),
        @Json(name = "FwUpgrade") FW_UPGRADE("FwUpgrade"),
        @Json(name = "Reset") RESET("Reset"),
        @Json(name = "LanDhcpFixedIpId") LAN_DHCP_FIXED_IP_ID("LanDhcpFixedIpId"),
        @Json(name = "LanDhcpFixedIp") LAN_DHCP_FIXED_IP("LanDhcpFixedIp"),
        @Json(name = "SmartWifi") SMART_WIFI("SmartWifi"),
        @Json(name = "WlanSupported") WLAN_SUPPORTED("WlanSupported"),
        @Json(name = "WifiSupported") WIFI_SUPPORTED("WifiSupported"),
        @Json(name = "WlanInterface") WLAN_INTERFACE("WlanInterface"),
        @Json(name = "ScanChannel") SCAN_CHANNEL("ScanChannel"),
        @Json(name = "WlanMacFilteringMac") WLAN_MAC_FILTERING_MAC("WlanMacFilteringMac"),
        @Json(name = "WlanMacFiltering") WLAN_MAC_FILTERING("WlanMacFiltering"),
        @Json(name = "WlanAccessPoint") WLAN_ACCESS_POINT("WlanAccessPoint"),
        @Json(name = "WlanScheduleEnable") WLAN_SCHEDULE_ENABLE("WlanScheduleEnable"),
        @Json(name = "WlanSchedule") WLAN_SCHEDULE("WlanSchedule"),
        @Json(name = "WlanSecurity") WLAN_SECURITY("WlanSecurity"),
        @Json(name = "WlanScheduleTempSwitchOn") WLAN_SCHEDULE_TEMP_SWITCH_ON("WlanScheduleTempSwitchOn"),
        @Json(name = "WlanScheduleId") WLAN_SCHEDULE_ID("WlanScheduleId"),
        @Json(name = "WlanWPS") WLAN_WPS("WlanWPS"),
        @Json(name = "DdnsProviders") DDNS_PROVIDERS("DdnsProviders"),
        @Json(name = "DDNS") DDNS("DDNS"),
        @Json(name = "Report") REPORT("Report"),
        @Json(name = "WlanWpsStartPairing") WLAN_WPS_START_PAIRING("WlanWpsStartPairing"),
        @Json(name = "WlanScheduleDelById") WLAN_SCHEDULE_DEL_BY_ID("WlanScheduleDelById"),
        @Json(name = "PcDevicesMacUrlsId") PC_DEVICES_MAC_URLS_ID("PcDevicesMacUrlsId"),
        @Json(name = "PcDevicesMacUrls") PC_DEVICES_MAC_URLS("PcDevicesMacUrls"),
        @Json(name = "PcDevicesMacSchedules") PC_DEVICES_MAC_SCHEDULES("PcDevicesMacSchedules"),
        @Json(name = "PcDevicesMac") PC_DEVICES_MAC("PcDevicesMac"),
        @Json(name = "PcDevices") PC_DEVICES("PcDevices"),
        @Json(name = "ParentalCtrl") PARENTAL_CTRL("ParentalCtrl"),
        @Json(name = "FirewallServicesId") FIREWALL_SERVICES_ID("FirewallServicesId"),
        @Json(name = "FirewallServices") FIREWALL_SERVICES("FirewallServices"),
        @Json(name = "Firewall") FIREWALL("Firewall"),
        @Json(name = "PortNatId") PORT_NAT_ID("PortNatId"),
        @Json(name = "PortNat") PORT_NAT("PortNat"),
        @Json(name = "IpNatId") IP_NAT_ID("IpNatId"),
        @Json(name = "IpNat") IP_NAT("IpNat"),
        @Json(name = "NAT") NAT("NAT"),
        @Json(name = "NotificationsEmail") NOTIFICATIONS_EMAIL("NotificationsEmail"),
        @Json(name = "Notifications") NOTIFICATIONS("Notifications"),
        @Json(name = "PcDevicesMacServicesId") PC_DEVICES_MAC_SERVICES_ID("PcDevicesMacServicesId"),
        @Json(name = "PcDevicesMacServices") PC_DEVICES_MAC_SERVICES("PcDevicesMacServices"),
        @Json(name = "PcDevicesMacSchedulesId") PC_DEVICES_MAC_SCHEDULES_ID("PcDevicesMacSchedulesId"),
        @Json(name = "ParentalCtrlUrls") PARENTAL_CTRL_URLS("ParentalCtrlUrls"),
        @Json(name = "SIP") SIP("SIP"),
        @Json(name = "Ring") RING("Ring"),
        @Json(name = "SoftphonePairedClients") SOFTPHONE_PAIRED_CLIENTS("SoftphonePairedClients"),
        @Json(name = "Softphone") SOFTPHONE("Softphone"),
        @Json(name = "WanDhcpv6") WAN_DHCPV6("WanDhcpv6"),
        @Json(name = "WanDhcp") WAN_DHCP("WanDhcp"),
        @Json(name = "Wan") WAN("Wan"),
        @Json(name = "WanDhcpRenew") WAN_DHCP_RENEW("WanDhcpRenew"),
        @Json(name = "OntAlarms") ONT_ALARMS("OntAlarms"),
        @Json(name = "Ont") ONT("Ont"),
        @Json(name = "WanDhcpv6Renew") WAN_DHCPV6_RENEW("WanDhcpv6Renew"),
        @Json(name = "OntGem") ONT_GEM("OntGem"),
        @Json(name = "OntGtc") ONT_GTC("OntGtc"),
        @Json(name = "WanSupported") WAN_SUPPORTED("WanSupported"),
        @Json(name = "Dsl") DSL("Dsl"),
        @Json(name = "DslStats") DSL_STATS("DslStats"),
        @Json(name = "3g") THREE_G("3g"),
        @Json(name = "3gNetwork") THREE_G_NETWORK("3gNetwork"),
        @Json(name = "3gPin") THREE_G_PIN("3gPin"),
        @Json(name = "3gPuk") THREE_G_PUK("3gPuk"),
        @Json(name = "Ppp") PPP("Ppp"),
        @Json(name = "PppRestart") PPP_RESTART("PppRestart"),
        @Json(name = "Qos") QOS("Qos"),
        @Json(name = "QosSupported") QOS_SUPPORTED("QosSupported"),
        @Json(name = "QosRun") QOS_RUN("QosRun"),
        @Json(name = "AccessWanGuiAllow") ACCESS_WAN_GUI_ALLOW("AccessWanGuiAllow"),
        @Json(name = "ReportDispatch") REPORT_DISPATCH("ReportDispatch"),
        @Json(name = "WlanMacFilteringRules") WLAN_MAC_FILTERING_RULES("WlanMacFilteringRules"),
        @Json(name = "AccessWanApi") ACCESS_WAN_API("AccessWanApi"),
        @Json(name = "AccessOspApiAllow") ACCESS_OSP_API_ALLOW("AccessOspApiAllow"),
        UNKNOWN("Unknown");

        /**
         * Define [value] to be able to be set dynamically so UNKNOWN values can set their original value.
         */
        var value: String = value
            private set

        companion object {
            /**
             * Returns the [Id] enum value corresponding to the given string value.
             *
             * NOTE: Any unknown value will return [UNKNOWN]. This makes it work for any new Router just out-of-the-box, as any new feature
             * will go thru the [UNKNOWN] case, BUT it would be a good practice to check without this when certifying a new Router, so we
             * can map any new feature to a new [Id] value.
             */
            fun fromValue(value: String): Id {
                return entries.find { it.value == value } ?: UNKNOWN.apply { this.value = value }
            }
        }
    }

    enum class Ops(val value: String) {
        @Json(name = "R") READ("R"),
        @Json(name = "W") WRITE("W"),
        @Json(name = "I") INVOKE("I"),
        @Json(name = "A") ADD("A"),
        @Json(name = "D") DELETE("D"),
        @Json(name = "Q") QUERY("Q")
    }
}
