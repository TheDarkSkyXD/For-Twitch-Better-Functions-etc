package com.github.andreyasadchy.xtra.model.gql.game

import com.github.andreyasadchy.xtra.model.gql.Error
import com.github.andreyasadchy.xtra.model.gql.PageInfo
import kotlinx.serialization.Serializable

@Serializable
class GameStreamsResponse(
    val errors: List<Error>? = null,
    val data: Data? = null,
) {
    @Serializable
    class Data(
        val game: Game,
    )

    @Serializable
    class Game(
        val streams: Streams,
    )

    @Serializable
    class Streams(
        val edges: List<Item>,
        val pageInfo: PageInfo? = null,
    )

    @Serializable
    class Item(
        val node: Stream,
        val cursor: String? = null,
    )

    @Serializable
    class Stream(
        val id: String? = null,
        val broadcaster: User? = null,
        val type: String? = null,
        val title: String? = null,
        val viewersCount: Int? = null,
        val previewImageURL: String? = null,
        val freeformTags: List<Tag>? = null,
    )

    @Serializable
    class User(
        val id: String? = null,
        val login: String? = null,
        val displayName: String? = null,
        val profileImageURL: String? = null,
    )

    @Serializable
    class Tag(
        val name: String? = null,
    )
}