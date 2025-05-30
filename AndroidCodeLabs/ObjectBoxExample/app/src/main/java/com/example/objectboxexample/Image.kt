package com.example.objectboxexample

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Index
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne

@Entity
data class Image(
    @Id var id: Long = 0,
    @Index var timestamp: Long? = null,
    var location: String? = null,
    var owner: String? = null,
) {
    @Backlink(to = "image")
    lateinit var users: ToMany<ImageUser>
}

@Entity
data class ImageUser(
    @Id var id: Long = 0,
    var user: String? = null
) {
    lateinit var image: ToOne<Image>
}