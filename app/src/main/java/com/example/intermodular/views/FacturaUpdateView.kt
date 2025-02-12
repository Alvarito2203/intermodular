package com.example.intermodular.views


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import androidx.compose.material3.TextField
import com.example.intermodular.models.Factura
import com.example.intermodular.viewmodels.FacturasViewModel


@Composable
fun FacturaUpdateView(navController: NavHostController, viewModel: FacturasViewModel, facturaId: String) {
    val factura = viewModel.state.facturasList.find { it.id == facturaId }
    val fecha = remember { mutableStateOf(factura?.fecha ?: "") }
    val emisor = remember { mutableStateOf(factura?.emisor ?: "") }
    val receptor = remember { mutableStateOf(factura?.receptor ?: "") }
    val baseImponible = remember { mutableStateOf(factura?.baseImponible?.toString() ?: "") }
    val iva = remember { mutableStateOf(factura?.iva?.toString() ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = fecha.value, onValueChange = { fecha.value = it }, label = { Text("Fecha") })
        TextField(value = emisor.value, onValueChange = { emisor.value = it }, label = { Text("Emisor") })
        TextField(value = receptor.value, onValueChange = { receptor.value = it }, label = { Text("Receptor") })
        TextField(value = baseImponible.value, onValueChange = { baseImponible.value = it }, label = { Text("Base Imponible") })
        TextField(value = iva.value, onValueChange = { iva.value = it }, label = { Text("IVA") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val total = baseImponible.value.toDoubleOrNull()?.let { base ->
                base + (iva.value.toDoubleOrNull() ?: 0.0)
            } ?: 0.0

            val updatedFactura = Factura(
                id = factura?.id ?: "",
                fecha = fecha.value,
                emisor = emisor.value,
                receptor = receptor.value,
                baseImponible = baseImponible.value.toDoubleOrNull() ?: 0.0,
                iva = iva.value.toDoubleOrNull() ?: 0.0,
                total = total
            )

            viewModel.actualizarFactura(updatedFactura)
            navController.popBackStack()
        }) {
            Text("Actualizar Factura")
        }
    }
}
