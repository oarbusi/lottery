package com.lottery.lottery.entities

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "ballot")
class Ballot(
    @Column(name = "lottery_participant_id") val lotteryParticipantId: Long,
    @Column(name = "ballot_number") val ballotNumber: Int,
    @Column(name = "date") val date: Date,
    @Column(name = "is_winner") val isWinner: Boolean
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0

    constructor() : this( -1, -1, Date(), false)
}