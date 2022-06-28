package aliesia.lykhova.nure.shelter.api.objects

import aliesia.lykhova.nure.shelter.api.interfaces.MakeBookAnimal
import aliesia.lykhova.nure.shelter.api.RetrofitClient

object MakeBookAnimal {
    private val URL = "http://192.168.1.15/api/en/animal/booked/{id}/"

    val retrofitService: MakeBookAnimal
        get () = RetrofitClient.getClient(URL).create(MakeBookAnimal::class.java)
}