package com.example.uicomponents

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var testText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        testText = findViewById(R.id.test_text)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_font, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.font_small -> { testText.textSize = 10f; true }
            R.id.font_medium -> { testText.textSize = 16f; true }
            R.id.font_large -> { testText.textSize = 20f; true }
            R.id.menu_about -> {
                Toast.makeText(this, "你点击了普通菜单项", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.color_red -> { testText.setTextColor(Color.RED); true }
            R.id.color_black -> { testText.setTextColor(Color.BLACK); true }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
