package org.example.app.data

import java.util.UUID

/**
 * PUBLIC_INTERFACE
 * Note data model representing a single note entity.
 */
data class Note(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var content: String,
    val createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis()
)
