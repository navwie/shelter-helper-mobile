package aliesia.lykhova.nure.shelter

import aliesia.lykhova.nure.shelter.data.Shelter
import aliesia.lykhova.nure.shelter.fragments.AnnouncementsFragment
import aliesia.lykhova.nure.shelter.fragments.PetsFragment
import aliesia.lykhova.nure.shelter.fragments.ProfileFragment
import aliesia.lykhova.nure.shelter.fragments.ShelterFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val shelter = intent.getSerializableExtra("shelter") as Shelter

        loadFragment(PetsFragment(), shelter.id)

        val bottomNav: BottomNavigationView = findViewById(R.id.navigation)
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.pets -> {
                    loadFragment(PetsFragment(), shelter.id)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.shelter -> {
                    loadFragment(ShelterFragment(), shelter.id)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.announcement -> {
                    loadFragment(AnnouncementsFragment(), shelter.id)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.profile -> {
                    loadProfileFragment(ProfileFragment())
                    return@setOnNavigationItemReselectedListener
                }
            }
        }

    }

    private fun loadFragment(fragment: Fragment, shelterId: Int) {
        val args = Bundle()
        args.putInt("shelterId", shelterId)
        fragment.arguments = args

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
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