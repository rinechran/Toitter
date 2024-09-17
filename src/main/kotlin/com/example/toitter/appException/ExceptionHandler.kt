package com.example.toitter.appException

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceException(status : Int, message: String) : RuntimeException(message)

data class ErrorResponse(
    val status: Int,
    val error: String,
)

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceException::class)
    fun handleException(ex: ResourceException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.FOUND.value(),
            error = ex.message ?: "Resource found"
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }
}