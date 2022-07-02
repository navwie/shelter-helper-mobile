package aliesia.lykhova.nure.shelter

import aliesia.lykhova.nure.shelter.api.objects.GetUserById
import aliesia.lykhova.nure.shelter.api.objects.UpdateUser
import aliesia.lykhova.nure.shelter.data.user.UpdateUserForm
import aliesia.lykhova.nure.shelter.data.user.UpdateUserResponse
import aliesia.lykhova.nure.shelter.data.user.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val name = findViewById<EditText>(R.id.changeName)
        val surname = findViewById<EditText>(R.id.changeSurname)
        val phone = findViewById<EditText>(R.id.changePhone)
        val email = findViewById<EditText>(R.id.changeEmail)
        val password = findViewById<EditText>(R.id.changePassword)

        val intent = Intent(this, EditProfileActivity::class.java)

        val changeButton = findViewById<Button>(R.id.submit)

        val apiService = GetUserById.retrofitService
        println(MainActivity.getUserId())
        apiService.getUserById(MainActivity.getAuthToken()!!, MainActivity.getUserId()!!).enqueue(object :
            Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                println(response.body())
                println(response.code())

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    name.setText(response.body()?.name)
                    surname.setText(response.body()?.surname)
                    phone.setText(response.body()?.phone)
                    email.setText(response.body()?.email)
                }

            }
        })

        changeButton.setOnClickListener {
            val api = UpdateUser.retrofitService

            val updateUserForm = UpdateUserForm(
                name.text.toString(),
                surname.text.toString(),
                email.text.toString(),
                phone.text.toString(),
                password.text.toString(),
            )


            api.updateUser(MainActivity.getAuthToken()!!, MainActivity.getUserId()!!, updateUserForm).enqueue(object :
                Callback<UpdateUserResponse> {
                override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                    println(t.message)
                    println(t.stackTrace)
                }

                override fun onResponse(
                    call: Call<UpdateUserResponse>,
                    response: Response<UpdateUserResponse>
                ) {
                    println(response.body())
                    println(response.code())

                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        startActivity(intent)
                    }
                }
            })
        }
    }

}