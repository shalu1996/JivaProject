package com.example.jivaproject.jiva.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jivaproject.R
import com.example.jivaproject.jiva.ui.theme.lightBlack
import com.example.jivaproject.jiva.ui.theme.lightGreen
import com.example.jivaproject.util.letters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameWidget(
    name: String,
    enabled: Boolean,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = lightGreen),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
    ) {
        val focusManager = LocalFocusManager.current
        Column(modifier = Modifier.padding(bottom = 20.dp)) {
            val maxChar = 30
            Text(
                text = stringResource(R.string.seller_name),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 20.dp, start = 16.dp),
                fontStyle = FontStyle.Italic,
                color = lightBlack
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
//                    .onFocusChanged {
//                        onDone.invoke(it.isFocused)
//                    }
//                   .focusable(isEnabled.value),
                value = name,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    containerColor = if(enabled)Color.White else Color.LightGray,
                    cursorColor = Color.Black,
                    disabledTextColor = Color.Black,
                    disabledLabelColor = Color.Green,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                enabled = enabled,
                onValueChange = {
                    if (it.length <= maxChar) {
                        onValueChange.invoke(it)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = {
                    Text("Enter the name", color = Color.LightGray)
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        onDone.invoke()
                        focusManager.clearFocus()
                    }),
            )
        }
    }
}