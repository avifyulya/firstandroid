package com.example.icikiwir

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editWidth: EditText
    private lateinit var editHeight: EditText
    private lateinit var editLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editWidth = findViewById(R.id.edt_width)
        editHeight = findViewById(R.id.edt_height)
        editLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onClick(view: View?) {
        if(view?.id == R.id.btn_calculate){
            val inputLength = editLength.text.toString().trim()
            val inputWidth = editWidth.text.toString().trim()
            val inputHeight = editHeight.text.toString().trim()

            var isEmptyFields = false
            var isInvalidDouble = false

            if(TextUtils.isEmpty(inputLength)){
                isEmptyFields = true
                editLength.error = "Field ini tidak boleh kosong"
            }
            if (TextUtils.isEmpty(inputWidth)){
                isEmptyFields = true
                editWidth.error = "Field ini tidak boleh kosong"
            }
            if (TextUtils.isEmpty(inputHeight)){
                isEmptyFields = true
                editHeight.error = "Field ini tidak boleh kosong"
            }
            val length = toDouble(inputLength)
            val width = toDouble(inputWidth)
            val height = toDouble(inputHeight)

            if (length == null){
                isInvalidDouble = true
                editLength.error = "Field ini harus berupa nomor yang valid"
            }
            if (width == null){
                isInvalidDouble = true
                editWidth.error = "Field ini harus berupa nomor yang valid"
            }
            if (height == null){
                isInvalidDouble = true
                editHeight.error = "Field ini harus berupa nomor yang valid"
            }
            if (!isEmptyFields && !isInvalidDouble){
                val volume = length as Double * width as Double * height as Double
                tvResult.text = volume.toString()
            }
        }
    }
    private fun toDouble(str: String): Double?{
        return try {
            str.toDouble()
        }catch (e: NumberFormatException){
            null
        }
    }
    companion object{
        private const val STATE_RESULT = "state_result"
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

}