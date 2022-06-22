package aliesia.lykhova.nure.shelter

import aliesia.lykhova.nure.shelter.data.Shelter
import aliesia.lykhova.nure.shelter.fragments.AnnouncementsFragment
import aliesia.lykhova.nure.shelter.fragments.PetsFragment
import aliesia.lykhova.nure.shelter.fragments.ShelterFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shelter = intent.getSerializableExtra("shelter") as Shelter

        setContentView(R.layout.activity_bottom_navigation)
        loadFragment(ShelterFragment(shelter))

        val bottomNav: BottomNavigationView = findViewById(R.id.navigation)
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.pets -> {
                    loadFragment(PetsFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.shelter -> {
                    loadFragment(ShelterFragment(shelter))
                    return@setOnNavigationItemReselectedListener
                }
                R.id.announcement -> {
                    loadFragment(AnnouncementsFragment())
                    return@setOnNavigationItemReselectedListener
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }
}