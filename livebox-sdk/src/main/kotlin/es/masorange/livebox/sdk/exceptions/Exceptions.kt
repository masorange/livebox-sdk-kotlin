package es.masorange.livebox.sdk.exceptions

import java.io.IOException

/** No Wi-Fi exception */
open class NoWifiException() : IOException("No Wi-Fi connection available")