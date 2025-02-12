package com.example.intermodular.states

import com.example.intermodular.models.Factura

data class FacturaState(
    val facturasList: List<Factura> = emptyList(), // Lista de facturas
    val isLoading: Boolean = false, // Estado de carga
    val errorMessage: String? = null // Mensaje de error si ocurre
)
