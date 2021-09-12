package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDataPicker(view)
        //Toast.makeText(this, "Your Toast Here", Toast.LENGTH_LONG).show()
        }


    }

    // Function
    fun clickDataPicker(view: View){
        val c = Calendar.getInstance()

        // These are current, getting from your mobile phone.
        // You can check the comments in the DOBCalculator from Course - Resources
        var day = c.get(Calendar.DAY_OF_MONTH)
        var month = c.get(Calendar.MONTH)
        var year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener {
                    view, year, monthOfYear, dayOfMonth ->

                    // In Java Month Is From 0-11, that's why we did +1 in it
                    val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"

                    // Now Make It Visible To The User
                    tvSelectedDate.setText(selectedDate)

                    // Formatted Date
                    var sdf =  SimpleDateFormat("dd/mm/yyy", Locale.ENGLISH)

                    // Parsing the formatted date
                    val theDate = sdf.parse(selectedDate)

                    // Current date in to minutes.
                    val selectedDateToMinutes = theDate!!.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    // Current date in to minutes.
                    val currentDateToMinutes = currentDate!!.time / 60000

                    /**
                     * Now to get the difference into minutes.
                     * We will subtract the selectedDateToMinutes from currentDateToMinutes.
                     * Which will provide the difference in minutes.
                     */
                    val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

                    // Set the difference in minutes to textview to show the user.
                    tvSelectedDateInMinutes.text = differenceInMinutes.toString()



                }
                , year,month,day
        )

        /**
         * Sets the maximal date supported by this in
         * milliseconds since January 1, 1970 00:00:00 in time zone.
         *
         * @param maxDate The maximal supported date.
         */
        // 86400000 is milliseconds of 24 Hours. Which is used to restrict the user to select today or past and not future day.
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show() // It is used to show the datePicker Dialog.

        // To Calculate in The Age in Day - Age/ 60 * 24
    }



}