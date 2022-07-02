package aliesia.lykhova.nure.shelter

import aliesia.lykhova.nure.shelter.data.shelter.Animal
import aliesia.lykhova.nure.shelter.fragments.BottomBookedFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class AdminAnimalCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_animal_card)

        val animal = intent.getSerializableExtra("animal") as Animal

        //Get fields for list item
        val nameTextView = findViewById<TextView>(R.id.name)
        val typeTextView = findViewById<TextView>(R.id.type)
        val genderTextView = findViewById<TextView>(R.id.gender)
        val weightTextView = findViewById<TextView>(R.id.weight)
        val sterelizedTextView = findViewById<TextView>(R.id.sterelized)
        val birthdayTextView = findViewById<TextView>(R.id.birthday)
        val photoImageView = findViewById<ImageView>(R.id.photo)
        val descriptionImageTextView = findViewById<TextView>(R.id.description)
        val bookedTextView = findViewById<TextView>(R.id.bookedText)
        val bookedButton = findViewById<Button>(R.id.bookedButton)

        nameTextView.text = animal.name
        typeTextView.text = animal.type
        genderTextView.text = animal.gender
        descriptionImageTextView.text = animal.description
        weightTextView.text = animal.weight.toString()
        sterelizedTextView.text = when(animal.sterelized) {
            true -> "Стерилізована"
            false -> "Не стерилізована"
        }
        birthdayTextView.text = animal.birthday.toString()

        Glide.with(this)
            .load("http://192.168.1.15/storage/images/" + animal.photo)
            .into(photoImageView)
    }
}