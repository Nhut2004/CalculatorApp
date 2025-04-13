package com.example.calculatorapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorapp.data.data.getData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
@Composable
fun CalculatorApp(modifier: Modifier = Modifier){

    val viewModel: CalculatorViewModel =viewModel()
    Calculator(modifier,viewModel)
}

val buttonList = getData()

@Composable
fun Calculator(modifier: Modifier = Modifier,viewModel: CalculatorViewModel) {

    val equalation = viewModel.equalation.collectAsState()
    val resultText = viewModel.resultText.collectAsState()
    Box(
        modifier = modifier.padding(8.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = equalation.value?:"",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 30.dp),
                color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text =resultText.value?:"",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
            ) {
                items(buttonList) {
                    CalculatorButton(it, onclick = {
                        viewModel.onclickButton(btn = it)
                    })
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(btn: String,onclick: () -> Unit) {
    Box(modifier = Modifier.padding(10.dp)) {
        FloatingActionButton(
            onClick = {onclick() },
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            contentColor = Color.White,
            containerColor = getColor(btn)
        ) {
            Text(
                text = btn,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun getColor(btn: String): Color {
    if (btn == "C" || btn == "AC") {
        return Color(0xFFF44336)
    }
    if (btn == "(" || btn == ")") {
        return Color.Gray
    }
    if (btn == "/" || btn == "+" || btn == "*" || btn == "-" || btn == "=") {
        return Color(0xFFFF9800)
    }

    return Color(0xFF00C8C9)

}