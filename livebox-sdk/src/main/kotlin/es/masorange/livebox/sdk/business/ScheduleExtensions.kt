package es.masorange.livebox.sdk.business

import es.masorange.livebox.sdk.domain.livebox.models.Schedule

/**
 * Represents the days of the week.
 * The index corresponds to the order of the days, starting from 0 for MONDAY.
 */
enum class WeekDay {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    companion object {
        fun fromIndex(index: Int): WeekDay = entries[index]
    }
}

/**
 * Represents a specific hour of a specific day of the week.
 * The hour is represented in 24-hour format (0-23).
 */
data class WeekDayHour(
    val dayOfWeek: WeekDay,
    val hour: Int
)

/**
 * Converts a [Schedule] to a [WeekDayHour].
 * The ID on [Schedule] represents the hours in a week, so only values between 1 and 168 (24 x 7) are valid.
 *
 * @throws IllegalArgumentException if the [Schedule] ID is not between 1 and 168.
 */
@Throws(IllegalArgumentException::class)
fun Schedule.toWeekDayHour(): WeekDayHour {
    val id = id.toInt()
    if (id !in 1..168)
        throw IllegalArgumentException("Schedule ID must be between 1 and 168, inclusive.")

    val index = id - 1
    val day = index / 24
    val hour = index % 24

    val dayOfWeek = WeekDay.fromIndex(day)

    return WeekDayHour(dayOfWeek, hour)
}

/**
 * Converts a [WeekDayHour] to a [Schedule].
 * The ID on [Schedule] is calculated as (dayIndex * 24 + hour + 1), where dayIndex is the index of the day in [WeekDay].
 *
 * @throws IllegalArgumentException if the WeekDayHour is invalid (day must be between 0 and 6, hour must be between 0 and 23).
 */
@Throws(IllegalArgumentException::class)
fun WeekDayHour.toSchedule(): Schedule {
    val dayIndex = WeekDay.entries.indexOf(this.dayOfWeek)
    if (dayIndex !in 0..6 || hour !in 0..23)
        throw IllegalArgumentException("Invalid WeekDayHour: day must be between 0 and 6, hour must be between 0 and 23.")

    val id = dayIndex * 24 + hour + 1
    return Schedule(id = id.toString())
}