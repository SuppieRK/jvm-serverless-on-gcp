package gdg.devparty.serverless.db

import io.micronaut.http.annotation.*

@Controller
class UserController(
        private val userRepository: UserRepository
) {
    @Post("/users")
    fun create(@Body user: User): User {
        return userRepository.create(user)
    }

    @Get("/users/{id}")
    fun read(@PathVariable("id") id: String): User? {
        return userRepository.read(id)
    }

    @Put("/users/{id}")
    fun update(@PathVariable("id") id: String, @Body user: User): User? {
        return userRepository.update(id, user)
    }

    @Delete("/users/{id}")
    fun delete(@PathVariable("id") id: String) {
        userRepository.delete(id)
    }

    @Get("/users")
    fun readAll(): List<User> {
        return userRepository.readAll()
    }
}