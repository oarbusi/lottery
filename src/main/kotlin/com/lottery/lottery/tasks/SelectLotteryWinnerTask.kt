package com.lottery.lottery.tasks

import com.lottery.lottery.entities.Ballot
import com.lottery.lottery.services.BallotService
import com.lottery.lottery.utils.Constants.Companion.MAX_BALLOT_NUMBER
import com.lottery.lottery.utils.Constants.Companion.MIN_BALLOT_NUMBER
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate
import kotlin.random.Random

@Component
class SelectLotteryWinnerTask(
    private val ballotService: BallotService
) {

    @Scheduled(cron = "0 0 0 * * *")
    fun selectWinner(date: LocalDate? = null) {
        val ballotWinner = Random.nextInt(MIN_BALLOT_NUMBER, MAX_BALLOT_NUMBER)
        val dateToFindWinner: LocalDate = date ?: LocalDate.now()
        val winnerBallots: List<Ballot> = getWinnerBallots(ballotWinner, dateToFindWinner)
            .onEach { it.isWinner = true }
        if (winnerBallots.isNotEmpty())
            ballotService.saveWinnerBallot(winnerBallots)
    }

    fun getWinnerBallots(winnerBallotNumber: Int, date: LocalDate): List<Ballot> {
        return ballotService.getBallotsByDate(date)
            .filter { it.ballotNumber == winnerBallotNumber }
    }
}