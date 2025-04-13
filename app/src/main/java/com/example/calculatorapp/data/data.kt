package com.example.calculatorapp.data

object data {

    fun getData(): List<String>{
        return listOf(
            "C", "(", ")", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "+",
            "1", "2", "3", "-",
            "AC", "0", ".", "=",
        )
    }

}