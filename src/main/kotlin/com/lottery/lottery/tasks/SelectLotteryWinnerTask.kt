package com.lottery.lottery.tasks

import com.lottery.lottery.services.BallotService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.sql.Date
import java.time.LocalDate

@Component
class SelectLotteryWinnerTask(
    private val ballotService: BallotService
) {

    @Scheduled(cron = "0 0 0 * * *")
    fun selectWinner() {
        //TODO: Implement this
        ballotService.getBallotsByDate(Date.valueOf(LocalDate.now()))
    }
}