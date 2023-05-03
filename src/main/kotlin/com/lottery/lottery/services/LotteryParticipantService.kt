package com.lottery.lottery.services

import com.lottery.lottery.data.RegisterParticipantVM
import com.lottery.lottery.entities.LotteryParticipant
import com.lottery.lottery.repositories.LotteryParticipantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LotteryParticipantService @Autowired constructor(private val lotteryParticipantRepository: LotteryParticipantRepository) {

    fun registerParticipant(registerParticipantVM: RegisterParticipantVM): LotteryParticipant {
        if(lotteryParticipantRepository.findByNameAndEmail(registerParticipantVM.name, registerParticipantVM.email).isPresent)
            throw Exception("Participant already registered")
        return lotteryParticipantRepository.save(registerParticipantVM.toLotteryParticipant())
    }

    fun getParticipants(): List<LotteryParticipant> = lotteryParticipantRepository.findAll()
}