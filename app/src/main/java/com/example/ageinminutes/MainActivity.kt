package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       btnDatePicker.setOnClickListener { view ->
           clickDatePicker(view)
           //Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show()
       }




    }

    // Function
    fun clickDatePicker(view: View) {
        val cal =  Calendar.getInstance();
        val year = cal.get(Calendar.YEAR);
        val month = cal.get(Calendar.MONTH);
        val day = cal.get(Calendar.DAY_OF_MONTH);

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, dayOfMonth ->
            Toast.makeText(this, "Year: $selectedYear, Month: $selectedMonth, Day: $dayOfMonth", Toast.LENGTH_LONG).show();

                val textForDate = "$dayOfMonth/$selectedMonth/$selectedYear"

                tvSelectedDate.setText(textForDate)

                val simple_date_format = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)

                // check the documentation for the SimpleDateFormat
                val theDate = simple_date_format.parse(textForDate) // parse(text -> Date) - changed to Date

                val selectedDateInMinutes = theDate!!.time / 60000 // converting the date into minutes

                // get the System Current time and then convert into the Date
                val currentDate =  simple_date_format.parse(simple_date_format.format(System.currentTimeMillis()))

                val currentDateInMinutes = currentDate!!.time / 60000 // converting the date into minutes

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

        },
            year, month, day
        )
        // 86400000 is the milliseconds of the day
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }



}