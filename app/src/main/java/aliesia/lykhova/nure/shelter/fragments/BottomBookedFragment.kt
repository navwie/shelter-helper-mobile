package aliesia.lykhova.nure.shelter.fragments

import aliesia.lykhova.nure.shelter.AnimalCardActivity
import aliesia.lykhova.nure.shelter.BottomNavigationActivity
import aliesia.lykhova.nure.shelter.MainActivity
import aliesia.lykhova.nure.shelter.R
import aliesia.lykhova.nure.shelter.api.objects.MakeBookAnimal
import aliesia.lykhova.nure.shelter.data.booked_animal.BookedAnimalForm
import aliesia.lykhova.nure.shelter.data.booked_animal.BookedAnimalResponse
import aliesia.lykhova.nure.shelter.data.shelter.Animal
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [BottomBookedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomBookedFragment(val animal: Animal) : BottomSheetDialogFragment() {
    var cal = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_booked, container, false)
        val submit = view.findViewById(R.id.submit) as Button
        val button = view.findViewById(R.id.dateTimePicker) as TextView

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }
        }

        val timeSetListener = OnTimeSetListener { view, minute, second ->
            cal.set(Calendar.MINUTE, minute)
            cal.set(Calendar.SECOND, second)
            updateDateInView(button)
        }



        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                TimePickerDialog(view.context,
                    timeSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    true).show()

                DatePickerDialog(view.context,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

        submit.setOnClickListener {
            val myFormat = "yyyy-MM-dd'T'HH:mm" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)

            val authToken = MainActivity.getAuthToken()
            val apiService = MakeBookAnimal.retrofitService
            val formDate = BookedAnimalForm(sdf.format(cal.getTime()))

            if (authToken != null) {
                apiService.makeBook(authToken, animal.id, formDate).enqueue(object :
                    Callback<BookedAnimalResponse> {
                    override fun onFailure(call: Call<BookedAnimalResponse>, t: Throwable) {
                        println(t.message)
                        println(t.stackTrace)
                    }

                    override fun onResponse(
                        call: Call<BookedAnimalResponse>,
                        response: Response<BookedAnimalResponse>
                    ) {
                        println(response.code())
                        println(response.body())

                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            animal.booked = cal.getTime() as Date?
                            val intent = Intent(view.context, AnimalCardActivity::class.java)
                            startActivity(intent)
                        }
                    }
                })
            }
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun updateDateInView(dateTimePicker: TextView) {
        val myFormat = "yyyy-MM-dd'T'HH:mm" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dateTimePicker.setText(sdf.format(cal.getTime()))
    }
}