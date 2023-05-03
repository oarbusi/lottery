package com.lottery.lottery.data

import com.lottery.lottery.entities.LotteryParticipant

data class RegisterParticipantVM (val name: String, val email: String) {
    fun toLotteryParticipant(): LotteryParticipant {
        return LotteryParticipant(name, email)
    }
}