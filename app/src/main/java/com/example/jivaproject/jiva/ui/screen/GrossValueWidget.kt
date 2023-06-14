package com.example.jivaproject.jiva.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jivaproject.R
import com.example.jivaproject.jiva.ui.theme.lightBlack
import com.example.jivaproject.jiva.ui.theme.lightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrossValueWidget(value: String, onValueChange: (String) -> Unit, onDone: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = lightGreen),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 30.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        val focusManager = LocalFocusManager.current
        val maxChar = 6
        Column(modifier = Modifier.padding(bottom = 20.dp)) {
            Text(
                text = stringResource(R.string.gross_weight),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 20.dp, start = 16.dp),
                fontStyle = FontStyle.Italic,
                color = lightBlack
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    modifier = Modifier
                        .weight(2f),
                    value = value,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        containerColor = Color.White,
                        cursorColor = Color.Black,
                        disabledLabelColor = Color.Green,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    onValueChange = {
                        if (it.length <= maxChar) {
                            onValueChange.invoke(it)
                        }
                    },
                    singleLine = true,
                    placeholder = {
                        Text(
                            stringResource(R.string.enter_the_value),
                            color = Color.LightGray
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onDone.invoke()
                            focusManager.clearFocus()
                        }),
                )
                Text(
                    text = stringResource(R.string.tonnes),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp)
                        .align(Alignment.CenterVertically),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                )
            }
        }
    }
}