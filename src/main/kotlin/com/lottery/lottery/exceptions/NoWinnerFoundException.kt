package com.lottery.lottery.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NoWinnerFoundException(message: String): Exception(message) {
}