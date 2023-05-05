package com.lottery.lottery.tasks

import com.lottery.lottery.entities.Ballot
import com.lottery.lottery.services.BallotService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.Mockito.*
import java.time.LocalDate

internal class SelectLotteryWinnerVMTaskTest {

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
    fun selectWinners_shouldCallService() {
        `when`(ballotService.getBallotsByDate(LocalDate.parse("2023-01-01")))
            .thenReturn(
                listOf(
                    Ballot(1, 1, LocalDate.now(), false),
                    Ballot(1, 2, LocalDate.now(), false),
                    Ballot(2, 3, LocalDate.now(), false),
                    Ballot(3, 4, LocalDate.now(), false),
                    Ballot(3, 5, LocalDate.now(), false),
                    Ballot(3, 6, LocalDate.now(), false),
                    Ballot(4, 7, LocalDate.now(), false),
                    Ballot(4, 8, LocalDate.now(), false),
                    Ballot(5, 9, LocalDate.now(), false),
                    Ballot(5, 10, LocalDate.now(), false),
                ))

        selectLotteryWinnerTask.selectWinner(date = LocalDate.parse("2023-01-01"))

        verify(ballotService)
    }
}