package aliesia.lykhova.nure.shelter.data.shelter

import java.io.Serializable;
import java.sql.Date

data class Animal(
    var id: Int,
    var name: String,
    var gender: String,
    var photo: String,
    var type: String,
    var weight: Int,
    var shelter_id: Int,
    var sterelized: Boolean,
    var description: String,
    var birthday: Date,
    var is_archive: Date,
    var booked: Date?,
    var booked_user_id: Int,


    ) : Serializable