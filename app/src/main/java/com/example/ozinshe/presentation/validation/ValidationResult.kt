package com.example.ozinshe.presentation.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
