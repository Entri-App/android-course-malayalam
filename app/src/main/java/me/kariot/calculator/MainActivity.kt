package me.kariot.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import me.kariot.calculator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var statement = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initClicks()
    }

    private fun initClicks() {
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)
        binding.btnZero.setOnClickListener(this)
        binding.btnZeroZero.setOnClickListener(this)
        binding.btnClear.setOnClickListener(this)
        binding.btnPercen.setOnClickListener(this)
        binding.btnBackSpace.setOnClickListener(this)
        binding.btnDiv.setOnClickListener(this)
        binding.btnMult.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)
        binding.btnPlus.setOnClickListener(this)
        binding.btnEqual.setOnClickListener(this)
        binding.btnDot.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.btn1.id -> {
                setText("1")
            }
            binding.btn2.id -> {
                setText("2")
            }
            binding.btn3.id -> {
                setText("3")
            }
            binding.btn4.id -> {
                setText("4")
            }
            binding.btn5.id -> {
                setText("5")
            }
            binding.btn6.id -> {
                setText("6")
            }
            binding.btn7.id -> {
                setText("7")
            }
            binding.btn8.id -> {
                setText("8")
            }
            binding.btn9.id -> {
                setText("9")
            }
            binding.btnZero.id -> {
                setText("0")
            }
            binding.btnZeroZero.id -> {
                setText("00")
            }
            binding.btnClear.id -> {
                statement = ""
                setText()
            }
            binding.btnPercen.id -> {
                setText("%")
            }
            binding.btnBackSpace.id -> {
                statement = statement.dropLast(1)
                binding.tvResult.text = statement
            }
            binding.btnDiv.id -> {
                setText("÷")
            }
            binding.btnMult.id -> {
                setText("x")
            }
            binding.btnMinus.id -> {
                setText("-")
            }
            binding.btnPlus.id -> {
                setText("+")
            }
            binding.btnEqual.id -> {
                if (statement.isBlank()) {
                    return
                }
                statement = statement.replace("x", "*")
                statement = statement.replace("÷", "/")
                val expression = Expression(statement)
                val result = expression.calculate()

                if (!result.isNaN()) {
                    binding.tvResult.text = result.toString()
                    statement = result.toString()
                } else {
                    statement = ""
                    binding.tvResult.text = ""
                }


            }
            binding.btnDot.id -> {
                setText(".")
            }
        }
    }

    fun setText(data: String = "") {
        if (isInputOperator(data) && isLastInputOperator()) {
            statement = statement.dropLast(1)
            setText(data)
            return
        }

        statement = "$statement$data"
        binding.tvResult.text = statement
    }

    private fun isLastInputOperator(): Boolean {
        var lastChar = statement.takeLast(1)
        return when (lastChar) {
            "+" -> true
            "-" -> true
            "÷" -> true
            "x" -> true
            "%" -> true
            "." -> true
            else -> false
        }
    }

    private fun isInputOperator(data: String): Boolean {
        return when (data) {
            "+" -> true
            "-" -> true
            "÷" -> true
            "x" -> true
            "%" -> true
            "." -> true
            else -> false
        }
    }
}