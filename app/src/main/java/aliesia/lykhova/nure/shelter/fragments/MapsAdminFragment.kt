package aliesia.lykhova.nure.shelter.fragments

import aliesia.lykhova.nure.shelter.MainActivity.Companion.getShelterId
import aliesia.lykhova.nure.shelter.R
import aliesia.lykhova.nure.shelter.api.objects.GetShelterById
import aliesia.lykhova.nure.shelter.data.Shelter
import aliesia.lykhova.nure.shelter.google.CustomInfoWindowForGoogleMap
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsAdminFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        val apiService = GetShelterById.retrofitService
        //val context = context

        apiService.getShelterById(getShelterId()!!).enqueue(object : Callback<Shelter> {
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

                val marker = googleMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(response.body()!!.latitude, response.body()!!.longitude))
                        .title(response.body()!!.name)
                        .snippet(response.body()!!.phone)
                )
                if (marker != null) {
                    marker.tag = response.body()!!
                }

                val shelter = LatLng(response.body()!!.latitude, response.body()!!.longitude)
                val zoomLevel = 17f
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shelter, zoomLevel))
                googleMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(context!!))
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.admin_map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}