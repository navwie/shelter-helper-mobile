package aliesia.lykhova.nure.shelter.data.user

import java.io.Serializable

data class UserLoginForm(
    var email: String,
    var password: String
): Serializable