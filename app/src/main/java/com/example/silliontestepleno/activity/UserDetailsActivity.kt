package com.example.silliontestepleno.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.silliontestepleno.R
import com.example.silliontestepleno.data.Address
import com.example.silliontestepleno.data.Coordinates
import com.example.silliontestepleno.data.CreditCard
import com.example.silliontestepleno.data.Employment
import com.example.silliontestepleno.data.Subscription
import com.example.silliontestepleno.data.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Suppress("DEPRECATION")
class UserDetailsActivity : AppCompatActivity() {

    private lateinit var userList: List<User>
    private lateinit var user: User
    private lateinit var employment: Employment
    private lateinit var address: Address
    private lateinit var coordinates: Coordinates
    private lateinit var creditCard: CreditCard
    private lateinit var subscription: Subscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        setupToolbar()

        val userListJson = intent.getStringExtra("user_list_json")
        if (userListJson != null) {
            val userListType = object : TypeToken<List<User>>() {}.type
            userList = Gson().fromJson<List<User>>(userListJson, userListType)

            val userId = intent.getIntExtra("user_id", -1)
            if (userId != -1) {
                try {
                    user = getUserDetails(userList, userId)
                    initUserData(user)
                    bindUserData()

                } catch (e: IllegalArgumentException) {

                    finish()
                }
            } else {
                finish()
            }
        } else {
            finish()
        }
    }

    private fun getUserDetails(userList: List<User>, userId: Int): User {
        val user = userList.getOrNull(userId)
        if (user == null) {
            throw IllegalArgumentException("User ID inválido")
        }
        return user
    }

    private fun initUserData(user: User) {

        employment = user.employment
        address = user.address
        coordinates = user.coordinates
        creditCard = user.creditCard
        subscription = user.subscription
    }

    private fun bindUserData() {
        val firstNameTextView = findViewById<TextView>(R.id.txt_first_input)
        val lastNameTextView = findViewById<TextView>(R.id.txt_last_input)
        val emailTextView = findViewById<TextView>(R.id.txt_email_input)
        val passwordTextView = findViewById<TextView>(R.id.txt_password_input)
        val idTextView = findViewById<TextView>(R.id.txt_id_input)
        val uidTextView = findViewById<TextView>(R.id.txt_uid_input)
        val usernameTextView = findViewById<TextView>(R.id.txt_username_input)
        val avatarImageView = findViewById<ImageView>(R.id.img_avatar)
        val genderTextView = findViewById<TextView>(R.id.txt_gender_input)
        val phonenumberTextView = findViewById<TextView>(R.id.txt_phone_number_input)
        val socialinsurancenumberTextView = findViewById<TextView>(R.id.txt_social_insurance_number_input)
        val dateofbirthTextView = findViewById<TextView>(R.id.txt_date_of_birth_input)
        val titleTextView = findViewById<TextView>(R.id.txt_employment_title_input)
        val keyskillTextView = findViewById<TextView>(R.id.txt_employment_key_skill_input)
        val cityTextView = findViewById<TextView>(R.id.txt_city_input)
        val streetnameTextView = findViewById<TextView>(R.id.txt_street_name_input)
        val streetaddressTextView = findViewById<TextView>(R.id.txt_street_address_input)
        val zipcodeTextView = findViewById<TextView>(R.id.txt_zip_code_input)
        val stateTextView = findViewById<TextView>(R.id.txt_state_input)
        val countryTextView = findViewById<TextView>(R.id.txt_country_input)
        val ccnumberTextView = findViewById<TextView>(R.id.txt_cc_number_input)
        val planTextView = findViewById<TextView>(R.id.txt_plan_input)
        val statusTextView = findViewById<TextView>(R.id.txt_status_input)
        val paymentmethodTextView = findViewById<TextView>(R.id.txt_payment_method_input)
        val termTextView = findViewById<TextView>(R.id.txt_term_input)
        val latTextView = findViewById<TextView>(R.id.txt_lat_input)
        val lngTextView = findViewById<TextView>(R.id.txt_lng_input)

        idTextView.text = user.id.toString()
        firstNameTextView.text = user.first_name
        lastNameTextView.text = user.last_name
        emailTextView.text = user.email
        passwordTextView.text = user.password
        uidTextView.text = user.uid
        usernameTextView.text = user.username
        genderTextView.text = user.gender
        phonenumberTextView.text = user.phone_number
        socialinsurancenumberTextView.text = user.social_insurance_number
        dateofbirthTextView.text = user.date_of_birth
        titleTextView.text = user.employment.title
        keyskillTextView.text = user.employment.key_skill
        cityTextView.text = user.address.city
        streetnameTextView.text = user.address.street_name
        streetaddressTextView.text = user.address.street_address
        zipcodeTextView.text = user.address.zip_code
        stateTextView.text = user.address.state
        countryTextView.text = user.address.country
        if (user.coordinates != null) {
            latTextView.text = user.coordinates.lat.toString()
            lngTextView.text = user.coordinates.lng.toString()

        } else {
            latTextView.text = "N/A"
            lngTextView.text = "N/A"
        }

        if (user.creditCard != null) {
            ccnumberTextView.text = user.creditCard.cc_number
        } else {

            ccnumberTextView.text = "N/A"

        }



        planTextView.text = user.subscription.plan
        statusTextView.text = user.subscription.status
        paymentmethodTextView.text = user.subscription.payment_method
        termTextView.text = user.subscription.term

        Glide.with(this)
            .load(user.avatar)
            .into(avatarImageView)
    }

    private fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true) // Habilita o botão de voltar
        supportActionBar?.title = "User Information"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // Volta para a tela anterior ao clicar no botão de voltar
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}