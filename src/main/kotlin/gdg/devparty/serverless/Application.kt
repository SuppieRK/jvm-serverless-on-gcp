package gdg.devparty.serverless

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
    build()
            .args(*args)
            .packages("gdg.devparty.serverless")
            .start()
}