package aliesia.lykhova.nure.shelter

import aliesia.lykhova.nure.shelter.api.objects.GetAllShelters
import aliesia.lykhova.nure.shelter.data.Shelter
import aliesia.lykhova.nure.shelter.google.CustomInfoWindowForGoogleMap
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val search = findViewById<SearchView>(R.id.mapSearchView)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // on below line we are getting the
                // location name from search view.
                val location: String = search.getQuery().toString()

                // below line is to create a list of address
                // where we will store the list of all address.
                var addressList: List<Address>? = null

                // checking if the entered location is null or not.
                if (location != null || location == "") {
                    // on below line we are creating and initializing a geo coder.
                    val geocoder = Geocoder(this@MapsActivity)
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    val address: Address = addressList!![0]

                    // on below line we are creating a variable for our location
                    // where we will add our locations latitude and longitude.
                    val latLng = LatLng(address.getLatitude(), address.getLongitude())

                    // on below line we are adding marker to that position.
                    mMap.addMarker(MarkerOptions().position(latLng).title(location))

                    // below line is to animate camera to that position.
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        var shelters = arrayListOf<Shelter>();
        val context = this;

        val ShelterService = GetAllShelters.sheltersRetrofitService;
        ShelterService.getShelterList().enqueue(object : Callback<ArrayList<Shelter>> {
            override fun onFailure(call: Call<ArrayList<Shelter>>, t: Throwable) {}

            override fun onResponse(
                call: Call<ArrayList<Shelter>>,
                response: Response<ArrayList<Shelter>>
            ) {
                shelters = response.body()!!

                var markers = arrayListOf<Marker?>();

                for (shelter in shelters) {
                    val marker = mMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(shelter.latitude, shelter.longitude))
                            .title(shelter.name)
                            .snippet(shelter.phone)
                    )
                    if (marker != null) {
                        marker.tag = shelter
                    };
                }

                val homeLatLng = LatLng(50.44751236754299, 30.517173122116105)
                val zoomLevel = 13f
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
                mMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(context))

                mMap.setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener { marker ->
                    val shelterIntent = Intent(context, BottomNavigationActivity::class.java)
                    val shelter = marker.tag as Shelter
                    shelterIntent.putExtra("shelter", marker.tag as Shelter);
                    //MainActivity.setShelterId(shelter.id)
                    startActivity(shelterIntent)
                })

            }
        })

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        mMap.isMyLocationEnabled = true
    }
}