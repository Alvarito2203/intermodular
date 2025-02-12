package com.example.intermodular.views



import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.intermodular.models.Factura
import com.example.intermodular.viewmodels.FacturasViewModel


@Composable
fun FacturaAddView(navController: NavHostController, viewModel: FacturasViewModel) {
    val id = remember { mutableStateOf("") }
    val fecha = remember { mutableStateOf("") }
    val emisor = remember { mutableStateOf("") }
    val receptor = remember { mutableStateOf("") }
    val baseImponible = remember { mutableStateOf("") }
    val iva = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campos para ingresar datos de la factura
        TextField(value = id.value, onValueChange = { id.value = it }, label = { Text("ID") })
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

            val factura = Factura(
                id = id.value,
                fecha = fecha.value,
                emisor = emisor.value,
                receptor = receptor.value,
                baseImponible = baseImponible.value.toDoubleOrNull() ?: 0.0,
                iva = iva.value.toDoubleOrNull() ?: 0.0,
                total = total
            )

            viewModel.agregarFactura(factura)
            navController.popBackStack()
            viewModel.obtenerFacturas()
        }) {
            Text("Agregar Factura")
        }
    }
}
