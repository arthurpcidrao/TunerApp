package com.example.tunerapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home : AppCompatActivity() {

    private lateinit var commandbar: List<Pair<Button,ImageView>>
    private var clickedButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        commandbar = listOf(
            Pair(findViewById(R.id.tuner), findViewById(R.id.tuner_image)),
            Pair(findViewById(R.id.strings), findViewById(R.id.strings_image)),
            Pair(findViewById(R.id.settings), findViewById(R.id.settings_image)),
            Pair(findViewById(R.id.tap), findViewById(R.id.tap_image)),
            Pair(findViewById(R.id.metronome), findViewById(R.id.metronome_image))
        )

        commandbar.forEach { (button, imageView) ->
            button.setOnClickListener{
                onButtonClicked(button, imageView)
            }
        }

        // define the tuner button as clicked when activity begins
        val initialButton = commandbar[0].first
        val initialImageView = commandbar[0].second
        initialImageView.setImageResource(R.drawable.diapason_icon)
        clickedButton = initialButton


    }

    private fun onButtonClicked(button: Button, imageView: ImageView){
        val buttonId = button.id

        commandbar.forEach{(btn, imgView) ->
            val unclickedDrawable = when (btn.id){
                R.id.tuner -> R.drawable.diapason_icon_gray
                R.id.strings -> R.drawable.guitar_gray
                R.id.settings -> R.drawable.setting_gray
                R.id.tap -> R.drawable.tap_gray
                R.id.metronome -> R.drawable.metronome_gray
                else -> R.drawable.diapason_icon_gray
            }
            imgView.setImageResource(unclickedDrawable)
        }

        val clickedDrawable = when(buttonId){
            R.id.tuner -> R.drawable.diapason_icon
            R.id.strings -> R.drawable.guitar
            R.id.settings -> R.drawable.setting
            R.id.tap -> R.drawable.tap
            R.id.metronome -> R.drawable.metronome
            else -> R.drawable.diapason_icon
        }

        imageView.setImageResource(clickedDrawable)
        clickedButton = button

    }
}