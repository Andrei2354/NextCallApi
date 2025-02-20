package com.example.nextcallapi

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nextcallapi.network.producto.model.ProductResponse

@Composable
fun ProductListScreen(
    productViewModel: ProductViewModel,
    context: Context,
    innerPaddingValues: PaddingValues
) {
    val isLoading: Boolean by productViewModel.isloading.observeAsState(initial = true)
    if (isLoading) {
        productViewModel.getAllProduct()
        LoadingScreen()
    } else {
        CompleteProductListScreen(productViewModel.ProductList.value!!, innerPaddingValues, context)
    }
}



@Composable
fun CompleteProductListScreen(productList: List<ProductResponse>, innerPaddingValues: PaddingValues, context: Context){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                modifier = Modifier.padding(top = 25.dp),
                text = "Lista de productos",
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(8f, TextUnitType.Em)
            )
        }
        if (productList.isEmpty()) {
            item{
                Text(
                    modifier = Modifier.padding(top = 25.dp),
                    text = "Lista vacía",
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(8f, TextUnitType.Em)
                )
            }
        } else {
            items(productList) { product ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    modifier = Modifier
                        .width(260.dp)
                        .padding(bottom = 25.dp)
                        .height(200.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = product.title,
                            textAlign = TextAlign.Center,
                            fontSize = TextUnit(5f, TextUnitType.Em),
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 10.dp)
                        )
                        FilledIconButton(
                            modifier = Modifier.size(25.dp),
                            onClick = { Toast.makeText(context, "producto añadido", Toast.LENGTH_SHORT).show()}
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Añadir Elemento"
                            )
                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(bottom = 10.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize()
                            .padding(start = 5.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(160.dp),
                            model = product.thumbnail,
                            contentDescription = "Imagen de ${product.title}"
                        )
                    }
                }
            }
        }
    }
}