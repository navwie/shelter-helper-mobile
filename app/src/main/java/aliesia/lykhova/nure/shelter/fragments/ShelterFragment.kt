package aliesia.lykhova.nure.shelter.fragments

import aliesia.lykhova.nure.shelter.MainActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aliesia.lykhova.nure.shelter.R
import aliesia.lykhova.nure.shelter.api.objects.GetShelterById
import aliesia.lykhova.nure.shelter.api.objects.Login
import aliesia.lykhova.nure.shelter.data.Shelter
import aliesia.lykhova.nure.shelter.data.user.UserLoginResponse
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

private const val SHELTER_ID = "shelterId"

/**
 * A simple [Fragment] subclass.
 * Use the [PlaceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShelterFragment() : Fragment() {
    private var shelterId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shelterId = it.getInt(SHELTER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shelter, container, false)

        getShelterById(view, shelterId!!)

        return view
    }

    companion object {
        fun newInstance(shelterId: Int) =
            ShelterFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SHELTER_ID, shelterId)
                }
            }
    }

    private fun getShelterById(view: View, shelterId: Int) {
        val apiService = GetShelterById.retrofitService

        apiService.getShelterById(shelterId).enqueue(object : Callback<Shelter> {
            override fun onFailure(call: Call<Shelter>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

            override fun onResponse(
                call: Call<Shelter>,
                response: Response<Shelter>
            ) {
                println(response.body())
                println(response.code())

                val name: TextView = view.findViewById<TextView>(R.id.nameText)
                val address: TextView = view.findViewById<TextView>(R.id.addressText)
                val phone: TextView = view.findViewById<TextView>(R.id.phoneText)
                val email: TextView = view.findViewById<TextView>(R.id.emailText)
                val city: TextView = view.findViewById<TextView>(R.id.cityText)
                val image: ImageView = view.findViewById<ImageView>(R.id.imageView)

                Glide.with(view.context)
                    .load("http://192.168.1.15/storage/images/" + response.body()?.photo)
                    .into(image)
                name.setText(response.body()?.name)
                address.setText(response.body()?.address)
                phone.setText(response.body()?.phone)
                email.setText(response.body()?.email)
                city.setText(response.body()?.city)

            }
        })
    }

}