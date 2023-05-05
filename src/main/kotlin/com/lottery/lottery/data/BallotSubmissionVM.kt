package com.lottery.lottery.data

import com.fasterxml.jackson.annotation.JsonProperty

class BallotSubmissionVM( @JsonProperty("participant_id") val lotteryParticipantId: Long) {
    constructor() : this(0) // default constructor for Jackson
}
