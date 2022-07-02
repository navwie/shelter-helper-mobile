package aliesia.lykhova.nure.shelter.fragments

import aliesia.lykhova.nure.shelter.EditProfileActivity
import aliesia.lykhova.nure.shelter.MainActivity
import aliesia.lykhova.nure.shelter.MainActivity.Companion.getAuthToken
import aliesia.lykhova.nure.shelter.MainActivity.Companion.getUserId
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aliesia.lykhova.nure.shelter.R
import aliesia.lykhova.nure.shelter.api.objects.GetUserById
import aliesia.lykhova.nure.shelter.api.objects.Login
import aliesia.lykhova.nure.shelter.data.user.User
import aliesia.lykhova.nure.shelter.data.user.UserLoginResponse
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val apiService = GetUserById.retrofitService
        println(getUserId())
        apiService.getUserById(getAuthToken()!!, getUserId()!!).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                println(response.body())
                println(response.code())

                val name = view.findViewById<TextView>(R.id.editTextTextPersonName)
                val surname = view.findViewById<TextView>(R.id.editTextTextPersonSurname)
                val phone = view.findViewById<TextView>(R.id.editTextPhone)
                val email = view.findViewById<TextView>(R.id.editTextTextEmailAddress)

                val changeProfile = view.findViewById<Button>(R.id.changeProfile)

                changeProfile.setOnClickListener {
                    val intent = Intent(view.context, EditProfileActivity::class.java)
                    startActivity(intent)
                }

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    name.setText(response.body()?.name)
                    surname.setText(response.body()?.surname)
                    phone.setText(response.body()?.phone)
                    email.setText(response.body()?.email)
                }

            }
        })

        return view
    }
}