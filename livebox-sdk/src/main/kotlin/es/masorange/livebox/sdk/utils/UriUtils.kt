package es.masorange.livebox.sdk.utils

/**
 * Adds parameters to the {param} placeholders on an URI template.
 */
fun String.addParams(vars: Map<String, String>): String {
    val regex = Regex("\\{([^}]+)\\}")

    return regex.replace(this) { match ->
        val key = match.groupValues[1]
        vars[key] ?: throw IllegalArgumentException("Missing value for placeholder {$key}")
    }
}

/**
 * Prepare a full MAC address (11:22:33:44:55:66) to be injected in an URI.
 */
fun String.toUriMac(): String {
    return this.replace(":", "").uppercase()
}