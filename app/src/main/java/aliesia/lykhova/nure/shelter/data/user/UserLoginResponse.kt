package aliesia.lykhova.nure.shelter.data.user

import java.io.Serializable

data class UserLoginResponse (
    var userId: Int,
    var role: Boolean,
    var token: String,
) : Serializable