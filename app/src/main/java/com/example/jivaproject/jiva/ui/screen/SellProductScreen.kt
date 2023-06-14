package com.example.jivaproject.jiva.ui.screen

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.jivaproject.R
import com.example.jivaproject.jiva.data.model.SellerTable
import com.example.jivaproject.jiva.ui.theme.darkGreen
import com.example.jivaproject.jiva.ui.viewmodel.SellerViewModel
import com.example.jivaproject.util.calculateGrossPrice
import com.example.jivaproject.util.calculateLoyaltyIndex
import com.example.jivaproject.util.changeTonnesToKg
import com.example.jivaproject.util.generateLoyaltyNumber
import com.example.jivaproject.util.roundOff
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun SellProductScreen(navController: NavController, viewModel: SellerViewModel) {
    val response = viewModel.villages.observeAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.insertVillage()
        viewModel.getVillages()
    }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        val name = remember { mutableStateOf("") }
        val loyaltyNo = remember { mutableStateOf("") }
        val villageName = remember { mutableStateOf("") }
        val pricePerKg = remember { mutableDoubleStateOf(0.00) }
        val grossPrice = remember { mutableStateOf("0") }
        val grossValue = remember { mutableStateOf("") }
        val loyaltyIndex = remember { mutableDoubleStateOf(0.0) }
        val isUserRegistered = remember { mutableStateOf(false) }

        Text(
            text = stringResource(R.string.mandi),
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 16.dp),
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        NameWidget(name.value, {
            name.value = it
        }) { focused ->
            if (!focused && name.value.isNotBlank()) {
                coroutineScope.launch {
                    val value = viewModel.getSellerByName(name.value)
                    if (value != null) {
                        loyaltyNo.value = value.loyaltyCardNumber
                        isUserRegistered.value = true
                    }
                }
            }
        }
        LoyaltyCardWidget(loyaltyNo.value, {
            loyaltyNo.value = it
        }) { focused ->
            if (!focused && loyaltyNo.value.isNotBlank()) {
                coroutineScope.launch {
                    val value = viewModel.getSellerByLoyalty(loyaltyNo.value)
                    if (value != null) {
                        name.value = value.name
                        isUserRegistered.value = true
                    }
                }
            }
        }
        response.value?.let {
            villageWidget(it) { village, price ->
                villageName.value = village
                pricePerKg.value = price
            }
        }
        GrossValueWidget(grossValue.value) {
            if (villageName.value.isNotBlank()) {
                grossValue.value = it
                viewModel.grossValue = it
                if (isUserRegistered.value)
                    loyaltyIndex.value = calculateLoyaltyIndex(true)
                else
                    loyaltyIndex.value = calculateLoyaltyIndex(false)
                if (grossValue.value.isNotBlank()) {
                    grossPrice.value = roundOff(
                        calculateGrossPrice(
                            loyaltyIndex.value,
                            pricePerKg.value,
                            it.changeTonnesToKg()
                        )
                    )
                }
            } else {
                Toast.makeText(context, "Please select village", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(R.string.gross_price), fontWeight = FontWeight.Bold)
            Text(text = "${grossPrice.value} INR", fontWeight = FontWeight.Bold)
        }
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = "${stringResource(R.string.applied_index)} ${loyaltyIndex.value}",
            color = Color.Green,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.padding(top = 20.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(
                containerColor = darkGreen,
                contentColor = Color.White
            ),
            onClick = {
                Timber.d("Seller ${name.value} ${loyaltyNo.value} ${villageName.value}")
                if ((name.value.isNotBlank() || loyaltyNo.value.isNotBlank()) && villageName.value.isNotBlank() && grossPrice.value.toString()
                        .isNotBlank()
                ) {
                    coroutineScope.launch {
                        if (checkIfUserExist(name.value, viewModel)) {
                            Timber.d("User already exist")
                            loyaltyIndex.value = calculateLoyaltyIndex(true)
                            navController.navigate("thankyou")
                        } else {
                            addSeller(
                                name.value,
                                generateLoyaltyNumber(),
                                villageName.value,
                                viewModel
                            )
                            viewModel.sellerName = name.value
                            viewModel.grossPrice = grossPrice.value
                            navController.navigate("thankyou")
                        }
                    }

                } else
                    Toast.makeText(context, "Please enter required Details", Toast.LENGTH_SHORT)
                        .show()

            }) {
            Text(text = stringResource(R.string.sell_my_produces), fontSize = 14.sp)
        }
    }
}

fun addSeller(name: String, loyaltyNo: String, village: String, viewModel: SellerViewModel) {
    viewModel.addSeller(SellerTable(name, loyaltyNo, village))
}

fun checkIfUserExist(name: String, viewModel: SellerViewModel): Boolean {
    return viewModel.checkUserExist(name)
}