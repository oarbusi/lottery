package com.lottery.lottery.controllers

import com.lottery.lottery.data.BallotSubmissionVM
import com.lottery.lottery.entities.Ballot
import com.lottery.lottery.services.BallotService
import org.springframework.web.bind.annotation.*

@RestController
class BallotController(val ballotService: BallotService) {
    @PostMapping("/ballot")
    fun submitBallot(@RequestBody ballotVM: BallotSubmissionVM): Ballot {
        return ballotService.submitBallot(ballotVM)
    }

    @GetMapping("/winner-ballot/{date}")
    fun getWinnerBallot(@PathVariable date: String): Ballot {
        return ballotService.getWinnerBallot(date)
    }

}