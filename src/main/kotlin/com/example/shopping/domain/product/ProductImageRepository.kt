package com.example.shopping.domain.product

import com.example.shopping.domain.image.ProductImage
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageRepository: JpaRepository<ProductImage, Long> {
}