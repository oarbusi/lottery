package com.lottery.lottery.repositories

import com.lottery.lottery.entities.Ballot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.Optional

@Repository
interface BallotRepository: JpaRepository<Ballot, Long> {

    fun findByDateAndIsWinner(date: LocalDate, winner: Boolean): Optional<Ballot>

    fun findByDate(date: LocalDate): List<Ballot>
}