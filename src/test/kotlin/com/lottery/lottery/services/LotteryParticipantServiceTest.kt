package com.lottery.lottery.services

import com.lottery.lottery.data.RegisterParticipantVM
import com.lottery.lottery.entities.LotteryParticipant
import com.lottery.lottery.exceptions.LotteryParticipantAlreadyExistException
import com.lottery.lottery.repositories.LotteryParticipantRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*

internal class LotteryParticipantServiceTest {

    private lateinit var closeable: AutoCloseable

    @Mock
    private lateinit var lotteryParticipantRepository: LotteryParticipantRepository

    @InjectMocks
    private lateinit var lotteryParticipantService: LotteryParticipantService

    var NAME_1 = "name1"
    var EMAIL_1 = "email1"
    var NAME_2 = "name2"
    var EMAIL_2 = "email2"
    var PARTICIPANT_ID = 1L

    @BeforeEach
    fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)
    }

    @AfterEach
    @Throws(Exception::class)
    fun tearDown() {
        closeable.close()
    }

    @Test
    fun getLotteryParticipants_whenThereAreLotteryParticipants_shouldReturnListOfLotteryParticipants() {
        `when`(lotteryParticipantRepository.findAll())
            .thenReturn(
                listOf(
                    LotteryParticipant( NAME_1, EMAIL_1),
                    LotteryParticipant( NAME_2, EMAIL_2)
                )
            )

        val lotteryParticipants = lotteryParticipantService.getParticipants()

        assertEquals(lotteryParticipants.size, 2)
        assertEquals(lotteryParticipants[0].name, NAME_1)
        assertEquals(lotteryParticipants[0].email, EMAIL_1)

        assertEquals(lotteryParticipants[1].name, NAME_2)
        assertEquals(lotteryParticipants[1].email, EMAIL_2)
    }

    @Test
    fun getLotteryParticipants_whenThereAreNoLotteryParticipants_shouldReturnEmptyList() {
        `when`(lotteryParticipantRepository.findAll()).thenReturn(emptyList())

        val lotteryParticipants = lotteryParticipantService.getParticipants()

        assertEquals(lotteryParticipants.size, 0)
    }

    @Test
    fun registerParticipant_whenParticipantIsNotRegistered_shouldRegisterParticipant() {
        `when`(lotteryParticipantRepository.findByNameAndEmail(NAME_1, EMAIL_1)).thenReturn(Optional.empty())
        `when`(lotteryParticipantRepository.save(any())).thenReturn(LotteryParticipant(NAME_1, EMAIL_1))

        val lotteryParticipant = lotteryParticipantService.registerParticipant(RegisterParticipantVM(NAME_1, EMAIL_1))

        assertEquals(lotteryParticipant.name, NAME_1)
        assertEquals(lotteryParticipant.email, EMAIL_1)
        verify(lotteryParticipantRepository).save(any())
    }

    @Test
    fun registerParticipant_whenParticipantIsAlreadyRegistered_shouldThrowLotteryParticipantAlreadyExistException() {

        `when`(lotteryParticipantRepository.findByNameAndEmail(NAME_1, EMAIL_1)).thenReturn(Optional.of(LotteryParticipant(NAME_1, EMAIL_1)))

        assertThrows(LotteryParticipantAlreadyExistException::class.java) {
            lotteryParticipantService.registerParticipant(RegisterParticipantVM(NAME_1, EMAIL_1))
        }
    }

    @Test
    fun isParticipantRegistered_whenParticipantIsRegistered_shouldReturnTrue() {
        `when`(lotteryParticipantRepository.findById(PARTICIPANT_ID)).thenReturn(Optional.of(LotteryParticipant(NAME_1, EMAIL_1)))

        val isRegistered = lotteryParticipantService.isParticipantRegistered(PARTICIPANT_ID)

        assertTrue(isRegistered)
    }

    @Test
    fun isParticipantRegistered_whenParticipantIsNotRegistered_shouldReturnFalse() {
        `when`(lotteryParticipantRepository.findById(PARTICIPANT_ID)).thenReturn(Optional.empty())

        val isRegistered = lotteryParticipantService.isParticipantRegistered(PARTICIPANT_ID)

        assertFalse(isRegistered)
    }


}