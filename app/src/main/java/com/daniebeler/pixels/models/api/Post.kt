package com.daniebeler.pixels.models.api

data class Post(
    val id: String,
    val mediaAttachments: List<MediaAttachment>,
    val account: Account
)