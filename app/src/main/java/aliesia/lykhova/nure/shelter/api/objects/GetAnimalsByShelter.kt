package aliesia.lykhova.nure.shelter.api.objects

import aliesia.lykhova.nure.shelter.api.RetrofitClient
import aliesia.lykhova.nure.shelter.api.interfaces.GetAnimalsByShelter

object GetAnimalsByShelter {
    private val GET_URL = "https://shelter-service.herokuapp.com/animals/"

    val retrofitService: GetAnimalsByShelter
        get() = RetrofitClient.getClient(GET_URL).create(GetAnimalsByShelter::class.java)
}