package aliesia.lykhova.nure.shelter

import aliesia.lykhova.nure.shelter.api.objects.GetShelterById
import aliesia.lykhova.nure.shelter.data.Shelter
import aliesia.lykhova.nure.shelter.fragments.AnnouncementsFragment
import aliesia.lykhova.nure.shelter.fragments.PetsFragment
import aliesia.lykhova.nure.shelter.fragments.ShelterFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val shelter = intent.getSerializableExtra("shelter") as Shelter

        loadPetsFragment(PetsFragment(), shelter.id)

        val bottomNav: BottomNavigationView = findViewById(R.id.navigation)
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.pets -> {
                    loadPetsFragment(PetsFragment(), shelter.id)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.shelter -> {
                    loadShelterFragment(ShelterFragment(), shelter)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.announcement -> {
                    loadAnnouncementFragment(AnnouncementsFragment(), shelter.id)
                    return@setOnNavigationItemReselectedListener
                }
            }
        }

    }

    private fun loadPetsFragment(fragment: Fragment, shelterId: Int){
        val args = Bundle()
        args.putInt("shelterId", shelterId)
        fragment.arguments = args

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }

    private fun loadShelterFragment(fragment: Fragment, shelter: Shelter){
        val args = Bundle()
        args.putSerializable("shelter", shelter)
        fragment.arguments = args

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }

    private fun loadAnnouncementFragment(fragment: Fragment, shelterId: Int){
        val args = Bundle()
        args.putInt("shelterId", shelterId)
        fragment.arguments = args

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }
}