package com.example.shopping.domain.auth

import com.example.shopping.common.ShoppingException
import com.example.shopping.domain.user.User
import com.example.shopping.domain.user.UserRepository
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SignupService @Autowired constructor(
    private val userRepository: UserRepository
) {
    fun signup(signupRequest: SignupRequest) {
        validateRequest(signupRequest)
        checkDuplicates(signupRequest.email)
        registerUser(signupRequest)
    }

    private fun registerUser(signupRequest: SignupRequest) {
        with(signupRequest) {
            val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
            val user = User(email, hashedPassword, name)
            userRepository.save(user)
        }
    }

    private fun checkDuplicates(email: String) {
        userRepository.findByEmail(email)?.let {
            throw ShoppingException("이미 사용중인 이메일임")
        }
    }

    private fun validateRequest(signupRequest: SignupRequest) {
        validateEmail(signupRequest.email)
        validateName(signupRequest.name)
        validatePassword(signupRequest.password)
    }

    private fun validatePassword(password: String) {
        if (password.trim().length !in 8..20) {
            throw ShoppingException("비밀번호는 공백 제외하고 8자 이상 20자 이하여야 함")
        }
    }

    private fun validateName(name: String) {
        if (name.trim().length !in 2..20) {
            throw ShoppingException("이름은 2자이상 20자 이하여야 함")
        }
    }

    private fun validateEmail(email: String) {
        val isNotValidEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
            .toRegex(RegexOption.IGNORE_CASE)
            .matches(email)
            .not()
        if (isNotValidEmail) {
            throw ShoppingException("이메일 형식 오류")
        }
    }
}