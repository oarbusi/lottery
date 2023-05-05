package com.lottery.lottery.services

import com.lottery.lottery.data.RegisterParticipantVM
import com.lottery.lottery.entities.LotteryParticipant
import com.lottery.lottery.exceptions.LotteryParticipantAlreadyExistException
import com.lottery.lottery.repositories.LotteryParticipantRepository
import org.springframework.stereotype.Service

@Service
class LotteryParticipantService(private val lotteryParticipantRepository: LotteryParticipantRepository) {

    fun registerParticipant(registerParticipantVM: RegisterParticipantVM): LotteryParticipant {
        if(lotteryParticipantRepository.findByNameAndEmail(registerParticipantVM.name, registerParticipantVM.email).isPresent)
            throw LotteryParticipantAlreadyExistException("Participant already registered")
        return lotteryParticipantRepository.save(LotteryParticipant(registerParticipantVM.name, registerParticipantVM.email))
    }

    fun isParticipantRegistered(id: Long): Boolean = lotteryParticipantRepository.findById(id).isPresent

    fun getParticipants(): List<LotteryParticipant> = lotteryParticipantRepository.findAll()
}