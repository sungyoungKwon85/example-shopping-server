package com.example.shopping.domain.auth

data class SigninRequest(
    val email: String,
    val password: String
)