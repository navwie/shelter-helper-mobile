package aliesia.lykhova.nure.shelter.api.objects

import aliesia.lykhova.nure.shelter.api.RetrofitClient
import aliesia.lykhova.nure.shelter.api.interfaces.GetAnnouncementsByShelter

object GetAnnouncementsByShelter {
    private val GET_URL = "http://192.168.1.15/api/en/announcements/{id}/"

    val retrofitService: GetAnnouncementsByShelter
        get() = RetrofitClient.getClient(GET_URL).create(GetAnnouncementsByShelter::class.java)
}