package com.example.uicomponents

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AlertDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alertdialog)

        findViewById<Button>(R.id.btn_show_dialog).setOnClickListener {
            showCustomDialog()
        }
    }

    private fun showCustomDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom, null)
        val username = dialogView.findViewById<EditText>(R.id.edit_username)
        val password = dialogView.findViewById<EditText>(R.id.edit_password)

        AlertDialog.Builder(this)
            .setTitle("ANDROID APP")
            .setView(dialogView)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Sign in") { _, _ ->
                Toast.makeText(
                    this,
                    "Username: ${username.text}, Password: ${password.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .create()
            .show()
    }
}
