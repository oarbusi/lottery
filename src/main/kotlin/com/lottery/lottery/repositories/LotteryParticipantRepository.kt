package com.lottery.lottery.repositories

import com.lottery.lottery.entities.LotteryParticipant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LotteryParticipantRepository: JpaRepository<LotteryParticipant, Long> {
    fun findByNameAndEmail(name: String, email: String): Optional<LotteryParticipant>
}