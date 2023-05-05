package com.lottery.lottery.controllers

import com.lottery.lottery.data.RegisterParticipantVM
import com.lottery.lottery.entities.LotteryParticipant
import com.lottery.lottery.services.LotteryParticipantService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LotteryParticipantController(val lotteryParticipantService: LotteryParticipantService) {

    @PostMapping("/register")
    fun registerParticipant(@RequestBody registerParticipantVM: RegisterParticipantVM): LotteryParticipant {
        return lotteryParticipantService.registerParticipant(registerParticipantVM)
    }

    @GetMapping("/participants")
    fun getParticipants(): List<LotteryParticipant> = lotteryParticipantService.getParticipants()

}