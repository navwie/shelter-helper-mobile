package aliesia.lykhova.nure.shelter

import aliesia.lykhova.nure.shelter.api.objects.Registration
import aliesia.lykhova.nure.shelter.data.user.UserRegisterForm
import aliesia.lykhova.nure.shelter.data.user.UserRegisterResponse
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private var userRole: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun register(view: View) {
        val nameRegex = Regex("[a-zA-Z]{4,}")
        val phoneRegex = Regex("[+][0-9]{4,}")
        val emailRegex = Regex("\\w+@\\w+\\.\\w+")
        val passwordRegex = Regex("[a-zA-Z0-9]{4,}")

        val name: EditText = findViewById<EditText>(R.id.editTextTextPersonName)
        val surname: EditText = findViewById<EditText>(R.id.editTextTextPersonSurname)
        val phone: EditText = findViewById<EditText>(R.id.editTextPhone)
        val email: EditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById<EditText>(R.id.editTextTextPassword)


//        if (!nameRegex.matches(name.text.toString())) {
//            name.setBackgroundColor(Color.parseColor("#FF0000"))
//            return
//        }
//        if(!nameRegex.matches(surname.text.toString())) {
//            surname.setBackgroundColor(Color.parseColor("#FF0000"));
//            return
//        }
//        if(!phoneRegex.matches(phone.text.toString())) {
//            phone.setBackgroundColor(Color.parseColor("#FF0000"));
//            return
//        }
//        if(!emailRegex.matches(email.text.toString())) {
//            email.setBackgroundColor(Color.parseColor("#FF0000"));
//            return
//        }
//        if(!passwordRegex.matches(password.text.toString())) {
//            password.setBackgroundColor(Color.parseColor("#FF0000"));
//            return
//        }

        val user = UserRegisterForm(
            name.text.toString(),
            surname.text.toString(),
            email.text.toString(),
            phone.text.toString(),
            password.text.toString(),
            userRole,
        );

        val mainActivity = Intent (this, MainActivity::class.java)
        //startActivity(mainActivity)
        println(userRole)
        val registrationService = Registration.retrofitService;
        registrationService.register(user).enqueue(object : Callback<UserRegisterResponse> {
            override fun onFailure(call: Call<UserRegisterResponse>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<UserRegisterResponse>, response: Response<UserRegisterResponse>) {
                println(response.code());
                println(response.body());
                //startActivity(mainActivity);
            }
        })
    }

    fun onRadioButtonClicked(view: View) {
        userRole = !userRole
    }
}