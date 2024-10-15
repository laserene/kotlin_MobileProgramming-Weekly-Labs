package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var textResult: TextView

    private var step = 0
    private var opBtnClicked = 0
    private var ERR_DIV_ZERO = 0
    private var op: Int = 0
    private var op1: Int = 0
    private var op2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textResult = findViewById(R.id.text_result)

        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnMinus).setOnClickListener(this)
        findViewById<Button>(R.id.btnMul).setOnClickListener(this)
        findViewById<Button>(R.id.btnDiv).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (val id = v?.id) {
            R.id.btn0 -> addDigit(0)
            R.id.btn1 -> addDigit(1)
            R.id.btn2 -> addDigit(2)
            R.id.btn3 -> addDigit(3)
            R.id.btn4 -> addDigit(4)
            R.id.btn5 -> addDigit(5)
            R.id.btn6 -> addDigit(6)
            R.id.btn7 -> addDigit(7)
            R.id.btn8 -> addDigit(8)
            R.id.btn9 -> addDigit(9)
            else -> {
                step += 1

                when (id) {
                    R.id.btnAdd -> {
                        opBtnClicked += 1

                        if (opBtnClicked >= 2) {
                            if (op == 1) {
                                op1 += op2
                            } else if (op == 2) {
                                op1 -= op2
                            } else if (op == 3) {
                                op1 *= op2
                            } else {
                                if (op2 == 0) {
                                    textResult.text = "Cannot divide zero"
                                } else {
                                    op1 /= op2
                                }
                            }
                        }

                        op = 1
                    }
                    R.id.btnMinus -> {
                        opBtnClicked += 1
                        op = 2
                    }
                    R.id.btnMul -> {
                        opBtnClicked += 1
                        op = 3
                    }
                    R.id.btnDiv -> {
                        opBtnClicked += 1
                        op = 4
                    }
                    R.id.btnEqual -> {
                        val result = calculate()

                        if (ERR_DIV_ZERO == 1) {
                            textResult.text = "Cannot divide zero."
                        } else {
                            textResult.text = "$result"
                        }

                        resetVars()
                    }
                }
            }
        }
    }

    private fun addDigit(digit: Int) {
        if (step == 0) {
            op1 = op1 * 10 + digit
            textResult.text = "$op1"
        } else if (step == 1) {
            op2 = op2 * 10 + digit
            textResult.text = "$op2"
        }
    }

    private fun calculate(): Float {
        var result = 0.0f
        when (op) {
            1 -> result = op1 + op2.toFloat()
            2 -> result = op1 - op2.toFloat()
            3 -> result = op1 * op2.toFloat()
            4 -> {
                if (op2 == 0) {
                    ERR_DIV_ZERO = 1
                } else {
                    result = op1 / op2.toFloat()
                }
            }
        }
        return result
    }

    private fun resetVars() {
        step = 0
        op1 = 0
        op2 = 0
    }
}