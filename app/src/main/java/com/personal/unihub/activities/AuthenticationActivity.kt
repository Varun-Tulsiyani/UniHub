package com.personal.unihub.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.personal.unihub.databases.DatabaseViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var viewModel: DatabaseViewModel

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent (R.layout.activity_auth)

        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)

        // Connect to MySQL Database
        viewModel.connectToDatabase()

        // Observe the connection LiveData to perform authentication when the connection is established
        viewModel.connection.observe(this, Observer { connection ->
            if (connection != null) {
                authenticateUser(connection, "exampleUser", "examplePassword")
            } else {
                Toast.makeText(this, "Failed to connect to database", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @Suppress("SameParameterValue")
    @DelicateCoroutinesApi
    private fun authenticateUser(connection: Connection, username: String, password: String) {
        GlobalScope.launch {
            try {
                val statement = connection.createStatement()

                val query =
                    "SELECT * FROM users WHERE username = '$username' AND password = '$password'"
                val resultSet: ResultSet = statement.executeQuery(query)

                if (resultSet.next()) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AuthenticationActivity,
                            "Authentication successful",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AuthenticationActivity,
                            "Authentication failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                resultSet.close()
                statement.close()

            } catch (e: SQLException) {
                e.printStackTrace()

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@AuthenticationActivity,
                        "Authentication error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
