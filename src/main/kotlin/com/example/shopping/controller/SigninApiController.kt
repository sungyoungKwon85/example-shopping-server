package com.example.shopping.controller

import com.example.shopping.common.ApiResponse
import com.example.shopping.domain.auth.JWTUtil
import com.example.shopping.domain.auth.SigninRequest
import com.example.shopping.domain.auth.SigninService
import com.example.shopping.domain.auth.UserContextHolder
import com.example.shopping.interceptor.TokenValidationInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/api/v1")
class SigninApiController @Autowired constructor(
    private val signinService: SigninService,
    private val userContextHolder: UserContextHolder
) {

    @PostMapping("/signin")
    fun signin(@RequestBody signinRequest: SigninRequest) =
        ApiResponse.ok(signinService.signin(signinRequest))

    @PostMapping("/refresh_token")
    fun refresh_token(
        @RequestParam("grant_type") grantType: String
    ): ApiResponse {
        if (grantType != TokenValidationInterceptor.GRANT_TYPE_REFRESH) {
            throw IllegalArgumentException("grant_type does not exist")
        }

        return userContextHolder.email?.let {
            ApiResponse.ok(JWTUtil.createToken(it))
        } ?: throw IllegalArgumentException("user info does not exist")
    }
}