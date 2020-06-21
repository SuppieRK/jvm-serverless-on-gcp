package gdg.devparty.serverless.db

import com.google.cloud.NoCredentials
import com.google.cloud.datastore.*
import io.micronaut.context.annotation.Property
import java.util.*
import javax.inject.Singleton

@Singleton
class UserRepository(
        @Property(name = "datasources.gcp.emulator.enabled", defaultValue = "false") var emulatorEnabled: Boolean = false
) {
    private val repository: Datastore by lazy {
        if (emulatorEnabled) {
            DatastoreOptions.newBuilder().setCredentials(NoCredentials.getInstance()).build().service
        } else {
            DatastoreOptions.getDefaultInstance().service
        }
    }

    private val keyFactory: KeyFactory by lazy {
        repository.newKeyFactory().setKind(User::class.java.name)
    }

    fun create(user: User): User = with(user) {
        if (id.isNullOrBlank()) {
            id = UUID.randomUUID().toString()
        }

        val entity = Entity.newBuilder()
                .setKey(keyFactory.newKey(user.id))
                .set("name", user.name)
                .build()

        repository.put(entity)
        return this
    }

    fun read(id: String): User? {
        return repository.get(keyFactory.newKey(id))?.run {
            User(
                    this.key.name,
                    this.getString("name")
            )
        }
    }

    fun update(id: String, user: User): User? {
        return read(id)?.run {
            name = user.name
            create(this)
        }
    }

    fun delete(id: String) {
        repository.delete(keyFactory.newKey(id))
    }

    fun readAll(): List<User> {
        return repository.run(
                Query.newEntityQueryBuilder().setKind(User::class.java.name).build()
        ).iterator().asSequence().map {
            User(
                    it.key.name,
                    it.getString("name")
            )
        }.toList()
    }
}