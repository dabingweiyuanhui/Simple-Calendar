package com.simplemobiletools.calendar.helpers

import android.content.Context
import com.simplemobiletools.calendar.interfaces.WeeklyCalendar
import com.simplemobiletools.calendar.models.Event
import java.util.*

class WeeklyCalendarImpl(val mCallback: WeeklyCalendar, val mContext: Context) : DBHelper.GetEventsListener {
    var mEvents: List<Event>

    init {
        mEvents = ArrayList<Event>()
    }

    fun updateWeeklyCalendar(weekStartTS: Int) {
        val startTS = weekStartTS
        val endTS = startTS + WEEK_SECONDS
        DBHelper.newInstance(mContext).getEvents(startTS, endTS, this)
    }

    override fun gotEvents(events: MutableList<Event>) {
        mEvents = events
        mCallback.updateWeeklyCalendar(events)
    }
}
