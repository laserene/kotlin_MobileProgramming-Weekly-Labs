package com.example.simplelist

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(),  View.OnClickListener {
    private lateinit var textResult: EditText;
    private var mode: Int = 0;
    private var list = mutableListOf<Int>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textResult = findViewById(R.id.editText)
        findViewById<Button>(R.id.showButton).setOnClickListener(this)
        findViewById<RadioButton>(R.id.radioButton1).setOnClickListener(this)
        findViewById<RadioButton>(R.id.radioButton2).setOnClickListener(this)
        findViewById<RadioButton>(R.id.radioButton3).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (val id = v?.id) {
            R.id.radioButton1 -> mode = 1;
            R.id.radioButton1 -> mode = 2;
            R.id.radioButton1 -> mode = 3;
            R.id.showButton -> display()
        }
    }

    val s: String = "ad";

    private fun display() {
        val n: Int = textResult.text.toString().toInt()
        if (mode == 1) {
            for (i in 0 until n) {
                if (i % 2 == 0) list.add(i)
            }
        } else if (mode == 2) {
            for (i in 0 until n) {
                if (i % 2 != 0) list.add(i)
            }
        } else {
            for (i in 0 until n) {
                if (i * i < 0) list.add(i)
            }
        }
        list.clear()
    }
}