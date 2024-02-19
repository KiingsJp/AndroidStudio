package com.kings.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.kings.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        with(activityMainBinding) {
            btnExecutar.setOnClickListener {
                AlertDialog.Builder(it.context)
                    .setMessage("Teste setMessage")
                    .setTitle("Titulo")
                    .setCancelable(false)
                    .setNegativeButton("RECUSAR") { dialog, position ->
                        Toast.makeText(it.context, "Recusado", Toast.LENGTH_SHORT).show()
                    }
                    .setPositiveButton("ACEITAR") { dialog, position ->
                        Toast.makeText(it.context, "Aceitado", Toast.LENGTH_SHORT).show()
                    }
                    .setNeutralButton("Ajuda") { dialog, position ->
                        Toast.makeText(it.context, "Ajuda", Toast.LENGTH_SHORT).show()
                    }
                    .setIcon(R.drawable.baseline_warning_24)
                    .create().show()
            }
        }
    }
}