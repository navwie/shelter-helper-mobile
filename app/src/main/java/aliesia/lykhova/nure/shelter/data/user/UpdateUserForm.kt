package aliesia.lykhova.nure.shelter.data.user

import java.io.Serializable

data class UpdateUserForm(
    var name: String,
    var surname: String,
    var email: String,
    var phone: String,
    var password: String?,
): Serializable