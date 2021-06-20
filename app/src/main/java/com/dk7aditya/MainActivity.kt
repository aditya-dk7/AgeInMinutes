package com.dk7aditya

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var dateChosen: TextView
    private lateinit var selectedDateText: TextView
    private lateinit var ageInMinutes: TextView
    private lateinit var ageInMinutesText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val selectDate: Button = findViewById(R.id.selectDate)
        dateChosen = findViewById(R.id.dateChosen)
        selectedDateText = findViewById(R.id.selectedDateText)
        ageInMinutes = findViewById(R.id.ageInMinutes)
        ageInMinutesText = findViewById(R.id.ageInMinutesText)
        selectDate.setOnClickListener{
            selectBirthDate()
        }

    }
    private fun selectBirthDate() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(this, {
                _, yearSelected, monthSelected, dayOfMonthSelected ->
            val selectedDate = "$dayOfMonthSelected/${monthSelected+1}/$yearSelected"
            dateChosen.text = selectedDate
            selectedDateText.text = resources.getString(R.string.selected_date)
            val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time / 60000
            val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
            ageInMinutes.text = differenceInMinutes.toString()
            ageInMinutesText.text = resources.getString(R.string.age_in_minutes)
        },
            year,
            month,
            day)
        datePickerDialog.datePicker.maxDate = Date().time - 86400000
        datePickerDialog.show()
    }

}