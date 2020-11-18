package com.example.shopping.common

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@ControllerAdvice
@RestController
class ShoppingExceptionHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(ShoppingException::class)
    fun handleShoppingException(e: ShoppingException): ApiResponse {
        logger.error("API error", e)
        return ApiResponse.error(e.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ApiResponse {
        logger.error("API error", e)
        return ApiResponse.error("알 수 없는 오류")
    }
}