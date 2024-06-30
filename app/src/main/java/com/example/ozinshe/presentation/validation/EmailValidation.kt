package com.example.ozinshe.presentation.validation

import android.util.Patterns

object EmailValidation {

    fun execute (email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email не может быть пустым"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Некорректный email"
            )
        }
        else return ValidationResult(
            successful = true,
        )
    }
}