package es.masorange.livebox.sdk.exceptions

import java.io.IOException

/** Thrown when the router is needed and the device is not connected to a Wi-Fi network. */
open class NoWifiException() : IOException("No Wi-Fi connection available")