package com.lottery.lottery.services

import com.lottery.lottery.data.BallotSubmissionVM
import com.lottery.lottery.data.LotteryWinnerVM
import com.lottery.lottery.entities.Ballot
import com.lottery.lottery.exceptions.NoWinnerFoundException
import com.lottery.lottery.repositories.BallotRepository
import com.lottery.lottery.utils.Constants.Companion.MAX_BALLOT_NUMBER
import com.lottery.lottery.utils.Constants.Companion.MIN_BALLOT_NUMBER
import org.springframework.stereotype.Service
import java.time.LocalDate
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
                LocalDate.now(),
                false)
        )
    }

    fun getWinnerBallot(date: String): LotteryWinnerVM {
        val winnerBallot = ballotRepository.findByDateAndIsWinner(LocalDate.parse(date), true)
        if(winnerBallot.isPresent) {
            val winnerParticipant = lotteryParticipantService.getParticipantById(winnerBallot.get().lotteryParticipantId)
            return LotteryWinnerVM(winnerParticipant.name, winnerParticipant.email, winnerBallot.get().ballotNumber, winnerBallot.get().date.toString())
        }
        throw NoWinnerFoundException("No winner found for date $date")

    }

    fun getBallotsByDate(date: LocalDate): List<Ballot> {
        return ballotRepository.findByDate(date)
    }

    fun saveWinnerBallot(ballots: List<Ballot>) {
        ballotRepository.saveAll(ballots)
    }
}