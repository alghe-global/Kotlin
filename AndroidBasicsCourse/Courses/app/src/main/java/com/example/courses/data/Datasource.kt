package com.example.courses.data

import com.example.courses.R
import com.example.courses.model.Course

class Datasource() {
    fun loadCourses(): List<Course> {
        return listOf(
            Course(R.string.course1, R.drawable.course1, 58),
            Course(R.string.course2, R.drawable.course2, 121),
            Course(R.string.course3, R.drawable.course3, 78),
            Course(R.string.course4, R.drawable.course4, 118),
            Course(R.string.course5, R.drawable.course5, 423),
            Course(R.string.course6, R.drawable.course6, 92),
            Course(R.string.course7, R.drawable.course7, 165),
            Course(R.string.course8, R.drawable.course8, 164),
            Course(R.string.course9, R.drawable.course9, 326),
            Course(R.string.course10, R.drawable.course10, 305),
            Course(R.string.course11, R.drawable.course11, 212),
            Course(R.string.course12, R.drawable.course12, 172),
            Course(R.string.course13, R.drawable.course13, 321),
            Course(R.string.course14, R.drawable.course14, 118)
        )
    }
}