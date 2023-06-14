package com.example.jivaproject.jiva.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jivaproject.R
import com.example.jivaproject.jiva.ui.theme.darkGreen
import com.example.jivaproject.jiva.ui.viewmodel.SellerViewModel

@Composable
fun ThankYouScreen(navController: NavController, viewModel: SellerViewModel) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.cart), contentDescription = "Cart",
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 30.dp)
            )
            Text(
                text = "${stringResource(id = R.string.thank_you)} ${viewModel.sellerName} ${
                    stringResource(
                        id = R.string.thank_you_2
                    )
                }",
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 20.dp),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 30.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "${stringResource(id = R.string.receive_text_1)} ${viewModel.grossPrice} ${
                    stringResource(
                        id = R.string.receive_text_2
                    )
                } ${viewModel.grossValue} ${stringResource(id = R.string.receive_text_3)}",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 20.dp),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(
                containerColor = darkGreen,
                contentColor = Color.White
            ),
            onClick = { navController.popBackStack() }) {
            Text(text = stringResource(R.string.sell_more), fontSize = 14.sp)
        }
    }
}

