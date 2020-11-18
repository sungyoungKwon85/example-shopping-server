package com.example.shopping.domain.image

import com.example.shopping.domain.jpa.BaseEntity
import java.util.*
import javax.persistence.*

@Entity(name = "product_image")
class ProductImage(
    var path: String,
    var productId: Long? = null
): BaseEntity() {
}