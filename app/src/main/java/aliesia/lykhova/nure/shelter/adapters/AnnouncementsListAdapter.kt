package aliesia.lykhova.nure.shelter.adapters

import aliesia.lykhova.nure.shelter.R
import aliesia.lykhova.nure.shelter.data.Announcement
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

class AnnouncementsListAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Announcement>
) : BaseAdapter() {

    @SuppressLint("ServiceCast")
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

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
        val rowView = inflater.inflate(R.layout.announcement_list_item, parent, false)

        //Get fields for list item
        val topicTextView = rowView.findViewById(R.id.topic) as TextView
        val descriptionTextView = rowView.findViewById(R.id.description) as TextView
        val doneTextView = rowView.findViewById(R.id.done) as TextView
        val layout = rowView.findViewById(R.id.item_layout) as ConstraintLayout

        //Set up values for list item
        val announcement = getItem(position) as Announcement



        topicTextView.text = when(announcement.topic) {
            "dishes" -> "Посуд для тварин"
            "food" -> "Їжа для тварин"
            "toys" -> "Іграшки для тварин"
            "medicines" -> "Медичні препарати для тварин"
            else -> "Не знайдено"
        }
        descriptionTextView.text = announcement.description
        doneTextView.text = when(announcement.done) {
            null -> "Не виконано"
            else -> "Виконано"
        }


        //Example clicker for list item layout
        layout.setOnClickListener {
//            val intent = Intent(context, BottomNavigationActivity::class.java)
//            intent.putExtra("placeId", place.id)
//            context.startActivity(intent)
        }

        return rowView
    }

}