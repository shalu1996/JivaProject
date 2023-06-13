package com.example.jivaproject.jiva.ui.screen

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jivaproject.jiva.ui.theme.lightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoyaltyCardWidget(
    loyaltyCardNo: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = lightGreen),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 30.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(modifier = Modifier.padding(bottom = 20.dp)) {
            Text(
                text = "Loyalty card identifier",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 20.dp, start = 16.dp),
                fontStyle = FontStyle.Italic
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .onFocusChanged {
                        onFocusChange.invoke(it.isFocused)
                    }
                    .focusable(true),
                value = loyaltyCardNo,
                enabled = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    containerColor = Color.White,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.Green,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    onValueChange.invoke(it)
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
            )
        }
    }
}