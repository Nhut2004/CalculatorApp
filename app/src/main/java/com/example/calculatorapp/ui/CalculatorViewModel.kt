package com.example.calculatorapp.ui


import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.mozilla.javascript.Context

class CalculatorViewModel : ViewModel() {

    private val _equalation = MutableStateFlow("")
    val equalation = _equalation.asStateFlow()

    private val _resultText = MutableStateFlow("0")
    val resultText = _resultText.asStateFlow()


    fun onclickButton(btn: String) {
        Log.i("Clicked Button", btn)

        _equalation.value?.let {
            if (btn == "AC") {
                _equalation.value = ""
                _resultText.value = "0"
                return
            }

            if (btn == "C") {
                if (it.isNotEmpty()) {
                    _equalation.value = it.substring(0, it.length - 1)

                }
                return
            }

            if (btn == "=") {
                _equalation.value = _resultText.value
                return
            }

            _equalation.value = it + btn

            Log.i("equalation", _equalation.value)

            try {
                _resultText.value=calculatedResult(_equalation.value.toString())
            }catch (_: Exception){}

        }
    }

    fun calculatedResult(equation: String): String{
        val context: Context = Context.enter()
        context.optimizationLevel = -1
        val scriptable  = context.initStandardObjects()
        var finalResult = context.evaluateString(scriptable,equation,"Javascript",1,null).toString()
        if(finalResult.endsWith(".0")){
            finalResult = finalResult.replace(".0","")
        }
        return finalResult


    }

}


