package aliesia.lykhova.nure.shelter.api.interfaces

import aliesia.lykhova.nure.shelter.data.Announcement
import aliesia.lykhova.nure.shelter.data.shelter.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetAnnouncementsByShelter {
    @GET("http://192.168.1.15/api/en/announcements/{id}/")
    fun getAnnouncementsList(@Path("id") int: Int): Call<ArrayList<Announcement>>
}