package com.kings.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.kings.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root) 

        with(activityMainBinding){
            btnExecutar.setOnClickListener{
                Snackbar.make(it, "Snack Bar", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
