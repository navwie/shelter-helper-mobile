package aliesia.lykhova.nure.shelter.adapters

import aliesia.lykhova.nure.shelter.AnimalCardActivity
import aliesia.lykhova.nure.shelter.R
import aliesia.lykhova.nure.shelter.data.shelter.Animal
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide

class AnimalsListAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Animal>
) : BaseAdapter()  {

    @SuppressLint("ServiceCast")
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.animal_list_item, parent, false)

        //Get fields for list item
        val nameTextView = rowView.findViewById(R.id.name) as TextView
        val typeTextView = rowView.findViewById(R.id.type) as TextView
        val genderTextView = rowView.findViewById(R.id.gender) as TextView
        val weightTextView = rowView.findViewById(R.id.weight) as TextView
        val sterelizedTextView = rowView.findViewById(R.id.sterelized) as TextView
        val birthdayTextView = rowView.findViewById(R.id.birthday) as TextView
        val photoImageView = rowView.findViewById(R.id.photo) as ImageView
        val layout = rowView.findViewById(R.id.item_layout) as ConstraintLayout


        //Set up values for list item
        val animal = getItem(position) as Animal

        nameTextView.text = animal.name
        typeTextView.text = animal.type
        genderTextView.text = animal.gender
        weightTextView.text = animal.weight.toString()
        sterelizedTextView.text = when(animal.sterelized) {
            true -> "Стерилізована"
            false -> "Не стерилізована"
        }
        birthdayTextView.text = animal.birthday.toString()

        Glide.with(rowView.context)
            .load("http://192.168.1.15/storage/images/" + animal.photo)
            .into(photoImageView)

        //Example clicker for list item layout
        layout.setOnClickListener {
            Toast.makeText(context, "this is toast message", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, AnimalCardActivity::class.java)
            intent.putExtra("animal", animal)
            context.startActivity(intent)
        }

        return rowView
    }

}