package aliesia.lykhova.nure.shelter.data

import java.io.Serializable;

data class Shelter (
    var id: Int,
    var name: String,
    var address: String,
    var city: String,
    var email: String,
    var longitude: Double,
    var latitude: Double,
    var phone: String,
    var photo: String,

) : Serializable