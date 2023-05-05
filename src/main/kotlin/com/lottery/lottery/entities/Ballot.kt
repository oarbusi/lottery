package com.lottery.lottery.entities

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "ballot")
class Ballot(
    @Column(name = "lottery_participant_id") val lotteryParticipantId: Long,
    @Column(name = "ballot_number") val ballotNumber: Int,
    @Column(name = "date") val date: LocalDate,
    @Column(name = "is_winner") var isWinner: Boolean
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0

    constructor() : this( -1, -1, LocalDate.now(), false)
}