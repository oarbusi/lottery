package com.lottery.lottery.services

import com.lottery.lottery.data.BallotSubmissionVM
import com.lottery.lottery.entities.Ballot
import com.lottery.lottery.exceptions.NoWinnerFoundException
import com.lottery.lottery.repositories.BallotRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.sql.Date
import java.util.*


internal class BallotServiceTest {

    private lateinit var closeable: AutoCloseable

    @Mock
    private lateinit var lotteryParticipantService: LotteryParticipantService

    @Mock
    private lateinit var ballotRepository: BallotRepository

    @InjectMocks
    private lateinit var ballotService: BallotService

    var DATE_STRING = "2023-01-01"
    var DATE = Date.valueOf("2023-01-01")
    var BALLOT_NUMBER_1 = 1
    var BALLOT_NUMBER_2 = 2

    @BeforeEach
    fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)
    }

    @AfterEach
    @Throws(Exception::class)
    fun tearDown() {
        closeable.close()
    }

    private var lotteryParticipantId = 1L

    @Test
    fun submitBallot_whenSubmitterIsNotRegistered_shouldThrowIllegalArgumentException() {
        `when`(lotteryParticipantService.isParticipantRegistered(lotteryParticipantId))
            .thenReturn(false)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ballotService.submitBallot(BallotSubmissionVM(lotteryParticipantId))
        }
    }

    @Test
    fun getWinnerBallot_whenWinnerBallotExists_shouldReturnWinnerBallot() {
        `when`(ballotRepository.findByDateAndIsWinner(DATE, true))
            .thenReturn(Optional.of(Ballot(lotteryParticipantId, BALLOT_NUMBER_1, DATE, true)))

        val ballot = ballotService.getWinnerBallot(DATE_STRING)

        assertEquals(ballot.lotteryParticipantId, lotteryParticipantId)
        assertEquals(ballot.date, DATE)
        assertEquals(ballot.isWinner, true)
    }

    @Test
    fun getWinnerBallot_whenWinnerBallotDoesNotExists_shouldThrowNoWinnerFoundException() {
        `when`(ballotRepository.findByDateAndIsWinner(DATE, true))
            .thenReturn(Optional.empty())

        Assertions.assertThrows(NoWinnerFoundException::class.java) {
            ballotService.getWinnerBallot(DATE_STRING)
        }
    }

    @Test
    fun getBallotsByDate_whenBallotsExist_shouldReturnBallots() {
        `when`(ballotRepository.findByDate(DATE))
            .thenReturn(
                listOf(
                    Ballot(lotteryParticipantId, BALLOT_NUMBER_1, DATE, false),
                    Ballot(lotteryParticipantId, BALLOT_NUMBER_2, DATE, false)
                )
            )

        val ballots = ballotService.getBallotsByDate(DATE)

        assertEquals(ballots.size, 2)
        assertEquals(ballots[0].lotteryParticipantId, lotteryParticipantId)
        assertEquals(ballots[0].date, DATE)
        assertEquals(ballots[0].isWinner, false)

        assertEquals(ballots[1].lotteryParticipantId, lotteryParticipantId)
        assertEquals(ballots[1].date, DATE)
        assertEquals(ballots[1].isWinner, false)
    }

    @Test
    fun getBallotsByDate_whenBallotsDoNotExist_shouldReturnEmptyList() {

        `when`(ballotRepository.findByDate(DATE)).thenReturn(emptyList())

        val ballots = ballotService.getBallotsByDate(DATE)

        assertEquals(ballots.size, 0)
    }
}