package com.lottery.lottery.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class LotteryParticipantAlreadyExistException(message: String): Exception(message) {
}