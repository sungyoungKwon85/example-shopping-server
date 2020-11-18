package com.example.shopping.domain.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
}