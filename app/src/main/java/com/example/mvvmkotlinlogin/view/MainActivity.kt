package com.example.mvvmkotlinlogin.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmkotlinlogin.data.ApiInterface
import com.example.mvvmkotlinlogin.repository.LoginRepository
import com.example.mvvmkotlinlogin.databinding.ActivityMainBinding
import com.example.mvvmkotlinlogin.viewModel.LoginViewModel
import com.example.mvvmkotlinlogin.viewModelFactory.LoginViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private val apiCall = ApiInterface.create()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    private lateinit var inputEmail: String
    private lateinit var inputPassword: String

    private var selectedLan = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this, LoginViewModelFactory(LoginRepository(apiCall))
        )
            .get(LoginViewModel::class.java)

        binding.image.setOnClickListener {
            when {
                binding.english.visibility == View.VISIBLE -> {
                    binding.arabic.visibility = View.VISIBLE
                    binding.english.visibility = View.GONE
                    selectedLan = "ar"
                    setLocate(selectedLan)

        //                val intent = Intent(this,MainActivity::class.java)
        //                startActivity(intent)

                }
                binding.arabic.visibility == View.VISIBLE -> {
                    binding.arabic.visibility = View.GONE
                    binding.english.visibility = View.VISIBLE
                    selectedLan = "en"
                    setLocate(selectedLan)

        //                val intent = Intent(this,MainActivity::class.java)
        //                startActivity(intent)
                }
                else -> {
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                }
            }
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener {
            if (validate()) {

//                inputEmail = binding.email.text.toString()
//                inputPassword = binding.password.text.toString()

                viewModel.login(inputEmail,inputPassword)


            }
//            else {
//                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show().toString()
//            }
        }

        viewModel.loginData.observe(this){
            Toast.makeText(this, "Successful...user: ${it.user_email}", Toast
                .LENGTH_SHORT).show()

            val userNickname =it.user_nicename
            val intent = Intent(this, HomePage::class.java)
            intent.putExtra("user_nickname",userNickname)
            this.startActivity(intent)
        }
        viewModel.toast.observe(this){

            Toast.makeText(this,"Invalid E-mail Address or Password",Toast.LENGTH_SHORT)
                .show().toString()
        }


    }

    private fun setLocate(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config,
            baseContext.resources.displayMetrics)
    }

    private fun validate(): Boolean {
//        val inputEmail = binding.email.text.toString()
//        val inputPassword: String = binding.password.text.toString()

        inputEmail = binding.email.text.toString()
        inputPassword = binding.password.text.toString()

        return if (inputEmail.isEmpty()) {
            Toast.makeText(this,"Email field is Empty",Toast.LENGTH_SHORT).show()
            false
        } else if (inputPassword.isEmpty()) {
            Toast.makeText(this,"Password field is Empty",Toast.LENGTH_SHORT).show()
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
            Toast.makeText(this,"Invalid E-mail Address",Toast.LENGTH_SHORT).show()
            false
        } else {
    //            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
            true
        }
    }
}

