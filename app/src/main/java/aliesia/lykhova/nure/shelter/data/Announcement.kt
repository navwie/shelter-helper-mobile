package aliesia.lykhova.nure.shelter.data

import java.io.Serializable
import java.sql.Date

data class Announcement(
    var id: Int,
    var topic: String,
    var description: String,
    var shelter_id: Int,
    var done: Date?
) : Serializable