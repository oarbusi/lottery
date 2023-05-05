package com.lottery.lottery.services

import com.lottery.lottery.data.BallotSubmissionVM
import com.lottery.lottery.entities.Ballot
import com.lottery.lottery.exceptions.NoWinnerFoundException
import com.lottery.lottery.repositories.BallotRepository
import com.lottery.lottery.utils.Constants.Companion.MAX_BALLOT_NUMBER
import com.lottery.lottery.utils.Constants.Companion.MIN_BALLOT_NUMBER
import org.springframework.stereotype.Service
import java.sql.Date
import java.time.Instant
import kotlin.random.Random

@Service
class BallotService(private val ballotRepository: BallotRepository, private val lotteryParticipantService: LotteryParticipantService) {

    fun submitBallot(ballot: BallotSubmissionVM): Ballot {
        if(!lotteryParticipantService.isParticipantRegistered(ballot.lotteryParticipantId))
            throw IllegalArgumentException("Participant with id ${ballot.lotteryParticipantId} is not registered")
        return ballotRepository.save(
            Ballot(
                ballot.lotteryParticipantId,
                Random.nextInt(MIN_BALLOT_NUMBER, MAX_BALLOT_NUMBER),
                Date.from(Instant.now()),
                false)
        )
    }

    fun getWinnerBallot(date: String): Ballot {
        val winner = ballotRepository.findByDateAndIsWinner(Date.valueOf(date), true)
        if(winner.isPresent)
            return winner.get()
        throw NoWinnerFoundException("No winner found for date $date")

    }

    fun getBallotsByDate(date: Date): List<Ballot> {
        return ballotRepository.findByDate(date)
    }

    fun saveWinnerBallot(ballots: List<Ballot>) {
        ballotRepository.saveAll(ballots)
    }
}