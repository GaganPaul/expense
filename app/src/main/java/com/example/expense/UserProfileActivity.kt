package com.example.expense

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val etProfileName = findViewById<EditText>(R.id.etProfileName)
        val etProfileEmail = findViewById<EditText>(R.id.etProfileEmail)
        val etProfilePassword = findViewById<EditText>(R.id.etProfilePassword)
        val btnSaveProfile = findViewById<Button>(R.id.btnSaveProfile)

        // Pre-fill with sample data (in real case, fetch from DB)
        etProfileName.setText("John Doe")
        etProfileEmail.setText("test@example.com")

        btnSaveProfile.setOnClickListener {
            val newName = etProfileName.text.toString().trim()
            val newPassword = etProfilePassword.text.toString().trim()

            if (newName.isEmpty()) {
                Toast.makeText(this, "Name cannot be empty!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Profile Updated Successfully!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
