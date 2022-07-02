package aliesia.lykhova.nure.shelter

import aliesia.lykhova.nure.shelter.MainActivity.Companion.getShelterId
import aliesia.lykhova.nure.shelter.api.objects.GetShelterById
import aliesia.lykhova.nure.shelter.data.Shelter
import aliesia.lykhova.nure.shelter.fragments.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class AdminBottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_bottom_navigation)

        val shelterId = getShelterId()

        loadFragment(PetsFragment(), shelterId!!)

        val bottomNav: BottomNavigationView = findViewById(R.id.admin_navigation)
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.pets -> {
                    loadFragment(PetsFragment(), shelterId)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.shelter -> {
                    loadFragment(ShelterFragment(), shelterId)
                    return@setOnNavigationItemReselectedListener
                }

                R.id.map -> {
                    loadFragment(MapsAdminFragment())
                    return@setOnNavigationItemReselectedListener
                }
//                R.id.announcement -> {
//                    loadAnnouncementFragment(AnnouncementsFragment(), shelter.id)
//                    return@setOnNavigationItemReselectedListener
//                }
//                R.id.profile -> {
//                    loadFragment(ProfileFragment(shelterId))
//                    return@setOnNavigationItemReselectedListener
//                }
            }
        }

    }

    private fun loadFragment(fragment: Fragment, shelterId: Int? = null){
        if (shelterId != null) {
            val args = Bundle()
            args.putInt("shelterId", shelterId)
            fragment.arguments = args
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.admin_frame_layout, fragment)
            commit()
        }
    }

    private fun loadProfileFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }
}