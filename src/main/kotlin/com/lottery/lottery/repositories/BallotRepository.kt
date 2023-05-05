package com.lottery.lottery.repositories

import com.lottery.lottery.entities.Ballot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date
import java.util.Optional

@Repository
interface BallotRepository: JpaRepository<Ballot, Long> {

    fun findByDateAndWinner(date: Date, winner: Boolean): Optional<Ballot>

    fun findByDate(date: Date): List<Ballot>
}