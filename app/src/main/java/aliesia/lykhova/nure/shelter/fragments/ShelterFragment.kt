package aliesia.lykhova.nure.shelter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aliesia.lykhova.nure.shelter.R
import aliesia.lykhova.nure.shelter.data.Shelter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

private const val SHELTER = "shelter"
/**
 * A simple [Fragment] subclass.
 * Use the [PlaceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShelterFragment() : Fragment() {
    private var shelter: Shelter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shelter = it.getSerializable(SHELTER) as Shelter?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_shelter, container, false)

        val name: TextView = view.findViewById<TextView>(R.id.nameText)
        val address: TextView = view.findViewById<TextView>(R.id.addressText)
        val phone: TextView = view.findViewById<TextView>(R.id.phoneText)
        val email: TextView = view.findViewById<TextView>(R.id.emailText)
        val city: TextView = view.findViewById<TextView>(R.id.cityText)
        val image: ImageView = view.findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load("http://192.168.1.15/storage/images/" + shelter?.photo)
            .into(image)
        name.setText(shelter?.name)
        address.setText(shelter?.address)
        phone.setText(shelter?.phone)
        email.setText(shelter?.email)
        city.setText(shelter?.city)

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
        fun newInstance(shelter: Shelter) =
            ShelterFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SHELTER, shelter)
                }
            }
    }

}