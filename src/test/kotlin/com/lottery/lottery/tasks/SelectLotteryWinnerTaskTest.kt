package com.lottery.lottery.tasks

import com.lottery.lottery.entities.Ballot
import com.lottery.lottery.services.BallotService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.Mockito.*
import java.sql.Date
import java.time.Instant

internal class SelectLotteryWinnerTaskTest {

    private lateinit var closeable: AutoCloseable

    @Mock
    private lateinit var ballotService: BallotService

    @InjectMocks
    private lateinit var selectLotteryWinnerTask: SelectLotteryWinnerTask

    @BeforeEach
    fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)
    }

    @Test
    fun selectWinner_shouldCallService() {
        selectLotteryWinnerTask.selectWinner()
        verify(ballotService)
    }

    @Test
    fun selectWinner_whenThereAreWinners_shouldSaveWinners() {
        `when`(ballotService.getBallotsByDate(Date.valueOf("2023-01-01")))
            .thenReturn(
                listOf(
                    Ballot(1, 1, Date.from(Instant.now()), false),
                    Ballot(1, 2, Date.from(Instant.now()), false),
                    Ballot(2, 3, Date.from(Instant.now()), false),
                    Ballot(3, 4, Date.from(Instant.now()), false),
                    Ballot(3, 5, Date.from(Instant.now()), false),
                    Ballot(3, 6, Date.from(Instant.now()), false),
                    Ballot(4, 7, Date.from(Instant.now()), false),
                    Ballot(4, 8, Date.from(Instant.now()), false),
                    Ballot(5, 9, Date.from(Instant.now()), false),
                    Ballot(5, 10, Date.from(Instant.now()), false),
                ))

        selectLotteryWinnerTask.selectWinner(date = Date.valueOf("2023-01-01"))

        verify(ballotService)
    }
}