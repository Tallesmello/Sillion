package com.example.silliontestepleno.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.silliontestepleno.R
import com.example.silliontestepleno.adapter.UserListAdapter
import com.example.silliontestepleno.data.User
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class HomeListActivity : AppCompatActivity() {

    private lateinit var userList: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_list)

        userList = parseUserJson()
        setupToolbar()

        val userListView = findViewById<ListView>(R.id.userListView)
        val adapter = UserListAdapter(this, userList)
        userListView.adapter = adapter

        userListView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("user_id", position)
            val userListJson = Gson().toJson(userList)
            intent.putExtra("user_list_json", userListJson)
            startActivity(intent)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    @SuppressLint("DiscouragedApi")
    private fun parseUserJson(): List<User> {
        val resourceId = resources.getIdentifier("data", "raw", packageName)
        val inputStream = resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val jsonContent = reader.use { it.readText() }

        val gson = Gson()
        return gson.fromJson(jsonContent, Array<User>::class.java).toList()
    }
}
