package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year.compareTo(other.year)
        month != other.month -> month.compareTo(other.month)
        else -> dayOfMonth.compareTo(other.dayOfMonth)
    }

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = addTimeIntervals(timeInterval, 1)
operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate = addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.number)


enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)
operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)


class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {

    override fun iterator(): Iterator<MyDate> = DateIterator(this)
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start
    override fun next(): MyDate {
        val result = current
        current = current.addTimeIntervals(TimeInterval.DAY, 1)
        return result
    }

    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}
