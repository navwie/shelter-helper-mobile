package aliesia.lykhova.nure.shelter.data.user

import aliesia.lykhova.nure.shelter.data.Shelter
import java.io.Serializable

data class UserLoginResponse (
    var userId: Int,
    var role: Boolean,
    var token: String,
    var shelter_id: Shelter
) : Serializable