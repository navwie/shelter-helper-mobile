package aliesia.lykhova.nure.shelter.data.user

import java.io.Serializable

data class User(
    var name: String,
    var surname: String,
    var email: String,
    var phone: String,
    var password: String,
    var role: Boolean?,
): Serializable