package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * A 1-hour slot of the week, as sent to and received from the router.
 *
 * Use [toWeekDayHour][es.masorange.livebox.sdk.business.toWeekDayHour] to know the day and hour it represents.
 *
 * @param id The slot number, from `"1"` (Monday 00:00-01:00) to `"168"` (Sunday 23:00-00:00).
 */
data class Schedule(
    @Json(name = "Id") val id: String
)