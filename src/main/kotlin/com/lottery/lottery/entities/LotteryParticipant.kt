package com.lottery.lottery.entities

import jakarta.persistence.*

@Entity
@Table(name = "lottery_participant")
class LotteryParticipant(
    @Column(name = "name") val name: String,
    @Column(name = "email") val email: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0

    constructor() : this( "", "")
}