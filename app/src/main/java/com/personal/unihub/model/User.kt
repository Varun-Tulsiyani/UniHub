package com.personal.unihub.model

import java.util.UUID

data class User(
    val userId: UUID,
    val emailAddress: String,
    val firstName: String,
    val lastName: String,
    val subscribed: Boolean
)