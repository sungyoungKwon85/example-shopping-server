package com.example.shopping.domain.product

enum class ProductStatus(private val status: String) {
    SELLABLE("selling"),
    SOLD_OUT("sold out")
}