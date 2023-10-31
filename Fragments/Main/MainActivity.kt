package com.kings.universigendateste;

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.kings.universigendateste.fragments.AgendaFragment
import com.kings.universigendateste.fragments.EventosFragment
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
        setColor()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_conteudo, AgendaFragment())
            .commit()

        findViewById<Button>(R.id.btn_agenda).setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_conteudo, AgendaFragment())
                .commit()
        }

        findViewById<Button>(R.id.btn_eventos).setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_conteudo, EventosFragment())
                .commit()
        }

    }

    private fun setColor() {
        val imageView = findViewById<ImageView>(R.id.ic_book)
        val textView = findViewById<TextView>(R.id.textToolBar)

        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            // PARA O TEMA ESCURO
            imageView.setImageResource(R.drawable.ic_book_night)
            textView.setTextColor(ContextCompat.getColor(this, R.color.black))
        } else {
            // PARA O TEMA CLARO
            imageView.setImageResource(R.drawable.ic_book_light)
            textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }
}
