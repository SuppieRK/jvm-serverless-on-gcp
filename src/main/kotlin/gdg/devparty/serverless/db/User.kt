package gdg.devparty.serverless.db

import io.micronaut.core.annotation.Introspected

@Introspected
data class User(
        var id: String? = null,
        var name: String? = null
)