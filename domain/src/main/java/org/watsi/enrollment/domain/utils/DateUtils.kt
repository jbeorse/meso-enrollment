package org.watsi.enrollment.domain.utils

import org.threeten.bp.Clock
import org.threeten.bp.DateTimeException
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import org.watsi.enrollment.domain.entities.DateAccuracy

object DateUtils {
    const val DATE_FORMAT = "dd-MM-yyyy" // Uganda dates are also day, month, year.

    fun formatLocalDate(localDate: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        return localDate.format(formatter)
    }

    fun isDateInFuture(localDate: LocalDate, clock: Clock = Clock.systemDefaultZone()): Boolean {
        return localDate.isAfter(LocalDate.now(clock))
    }

    fun dateWithAccuracyToAge(date: LocalDate,
                              accuracy: DateAccuracy,
                              clock: Clock = Clock.systemDefaultZone()
    ): Age? {
        return when (accuracy) {
            DateAccuracy.M -> {
                Age(getMonthsAgo(date, clock), AgeUnit.months)
            }
            DateAccuracy.Y -> {
                Age(getYearsAgo(date, clock), AgeUnit.years)
            }
            else -> null
        }
    }

    fun isValidBirthdate(year: Int, month: Int, day: Int, clock: Clock = Clock.systemDefaultZone()): Boolean {
        if (!(day in 1..31 && month in 1..12 && year >= 1900)) return false
        try {
            val birthdate = LocalDate.of(year, month, day)
            if (isDateInFuture(birthdate, clock)) return false
        } catch (e: DateTimeException) {
            return false
        }
        return true
    }

    fun getMonthsAgo(date: LocalDate, clock: Clock = Clock.systemDefaultZone()): Int {
        return ChronoUnit.MONTHS.between(date, LocalDate.now(clock)).toInt()
    }

    fun getYearsAgo(date: LocalDate, clock: Clock = Clock.systemDefaultZone()): Int {
        return ChronoUnit.YEARS.between(date, LocalDate.now(clock)).toInt()
    }

    fun getDaysAgo(date: LocalDate, clock: Clock = Clock.systemDefaultZone()): Int {
        return ChronoUnit.DAYS.between(date, LocalDate.now(clock)).toInt()
    }

    fun getSecondsAgo(time: Long, clock: Clock = Clock.systemDefaultZone()): Double {
        return (Instant.now(clock).toEpochMilli() - time) / 1000.0
    }
}

data class Age (val quantity: Int, val unit: AgeUnit) {

    fun toBirthdateWithAccuracy(clock: Clock = Clock.systemDefaultZone()): Pair<LocalDate, DateAccuracy> {
        return when (unit) {
            AgeUnit.months -> Pair(LocalDate.now(clock).minusMonths(quantity.toLong()), DateAccuracy.M)
            AgeUnit.years -> Pair(LocalDate.now(clock).minusYears(quantity.toLong()), DateAccuracy.Y)
        }
    }

    override fun toString() = "$quantity $unit"
}

enum class AgeUnit { months, years }
