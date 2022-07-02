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
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Spinner
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
    private var sort: Spinner? = null
    private var filter: Spinner? = null
    private var rootView: View? = null

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
        rootView =  inflater.inflate(R.layout.fragment_pets, container, false)

        sort = rootView?.findViewById(R.id.spinnerSorting)
        filter = rootView?.findViewById(R.id.spinnerSpecies)

        sort?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                getAnimals(rootView!!, shelterId!!)
            }

        }

        filter?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                getAnimals(rootView!!, shelterId!!)
            }

        }

        getAnimals(rootView!!, shelterId!!)
        return rootView
    }

    companion object {
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
                    val sortItem = sort?.selectedItem.toString()
                    val filterItem = filter?.selectedItem.toString()
                    var animals: ArrayList<Animal> = response.body()!!

                    animals = when (sortItem) {
                        "За ім`ям" -> { animals.sortedBy { it.name  }.toCollection(ArrayList<Animal>())}
                        "За віком" -> { animals.sortedBy { it.birthday  }.toCollection(ArrayList<Animal>())}
                        else -> { animals }
                    }

                    animals = when (filterItem) {
                        "Хлопчики" -> { animals.filter{ animal -> animal.gender.equals("Хлопчик") }.toCollection(ArrayList<Animal>())}
                        "Дівчинки" -> { animals.filter{ animal -> animal.gender.equals("Дівчинка") }.toCollection(ArrayList<Animal>())}
                        "Собака" -> { animals.filter{ animal -> animal.type.equals("Пес") }.toCollection(ArrayList<Animal>())}
                        "Кіт" -> { animals.filter{ animal -> animal.type.equals("Кіт") }.toCollection(ArrayList<Animal>())}
                        "Стерилізований" -> { animals.filter{ animal -> animal.sterelized }.toCollection(ArrayList<Animal>())}
                        "Не стерилізований" -> { animals.filter{ animal -> !animal.sterelized }.toCollection(ArrayList<Animal>())}
                        else -> { animals }
                    }

//                    println(sortItem)
//                    println(filterItem)

                    val listView = view.findViewById<ListView>(R.id.animals_list)
                    val adapter = AnimalsListAdapter(rootView!!.context, animals)
                    listView.adapter = adapter
                }
            }
        })
    }
}