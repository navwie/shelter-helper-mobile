package aliesia.lykhova.nure.shelter.fragments

import aliesia.lykhova.nure.shelter.MainActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aliesia.lykhova.nure.shelter.R
import aliesia.lykhova.nure.shelter.adapters.AnimalsListAdapter
import aliesia.lykhova.nure.shelter.api.objects.GetAnimalsByShelter
import aliesia.lykhova.nure.shelter.api.objects.Login
import aliesia.lykhova.nure.shelter.data.shelter.Animal
import aliesia.lykhova.nure.shelter.data.user.UserLoginResponse
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

private const val SHELTER_ID = "shelterId"
/**
 * A simple [Fragment] subclass.
 * Use the [PetsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PetsFragment() : Fragment() {
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
        val view =  inflater.inflate(R.layout.fragment_pets, container, false)
        getAnimals(view, shelterId!!)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(shelterId: Int) =
            PetsFragment().apply {
                arguments = Bundle().apply {
                    putInt(SHELTER_ID, shelterId)
                }
            }
    }

    private fun getAnimals(view: View, shelterId: Int) {
        val apiService = GetAnimalsByShelter.retrofitService
        apiService.getAnimalsList(shelterId).enqueue(object : Callback<ArrayList<Animal>> {
            override fun onFailure(call: Call<ArrayList<Animal>>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

            override fun onResponse(
                call: Call<ArrayList<Animal>>,
                response: Response<ArrayList<Animal>>
            ) {
                println(response.body())
                println(response.code())

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    val listView = view.findViewById<ListView>(R.id.animals_list)
                    val adapter = response.body()?.let { AnimalsListAdapter(view.context, it) }
                    listView.adapter = adapter
                }
            }
        })
    }
}