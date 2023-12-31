package com.example.jivaproject.jiva.ui.screen

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jivaproject.R
import com.example.jivaproject.jiva.ui.theme.lightBlack
import com.example.jivaproject.jiva.ui.theme.lightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoyaltyCardWidget(
    loyaltyCardNo: String,
    isEnabled:Boolean,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = lightGreen),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 30.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        val focusManager = LocalFocusManager.current
        Column(modifier = Modifier.padding(bottom = 20.dp)) {
            Text(
                text = stringResource(R.string.loyalty_card_identifier),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 20.dp, start = 16.dp),
                fontStyle = FontStyle.Italic, color = lightBlack

            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
//                    .onFocusChanged {
//                        onFocusChange.invoke(it.isFocused)
//                    }
//                    .focusable(true),
                value = loyaltyCardNo,
                enabled = isEnabled,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    containerColor = if(isEnabled)Color.White else Color.LightGray,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.Green,
                    disabledTextColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    onValueChange.invoke(it)
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = {
                    Text("Enter the loyalty no", color = Color.LightGray)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onDone.invoke()
                        focusManager.clearFocus()
                    }),

                )
        }
    }
}