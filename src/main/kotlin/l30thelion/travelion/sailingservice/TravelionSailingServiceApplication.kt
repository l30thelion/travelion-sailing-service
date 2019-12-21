package l30thelion.travelion.sailingservice

import l30thelion.travelion.sailingservice.SailingRepository.Sailing
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class TravelionSailingServiceApplication

fun main(args: Array<String>) {
    runApplication<TravelionSailingServiceApplication>(*args)
}

@RestController
@RequestMapping("/sailings")
class SailingsController {
    private val log = LoggerFactory.getLogger(javaClass)
    @GetMapping("/{shipCode}")
    fun getSailings(@PathVariable shipCode: String, @RequestHeader headers: Map<String, String>): List<Sailing> {
        log.info("Received request for shipCode {}, headers {}", shipCode, headers)
        Thread.sleep(2000)
        return SailingRepository.findByShipCode(shipCode)
    }
}

class SailingRepository {

    data class Sailing(val shipCode: String, val sailDate: String)

    companion object {

		fun findByShipCode(shipCode: String): List<Sailing> =
                sailings.filter { sailing -> sailing.shipCode == shipCode }

        private val sailings = listOf(
                Sailing("AQ", "2019-12-15"),
                Sailing("AQ", "2019-12-20"),
                Sailing("AQ", "2019-12-25"),
                Sailing("AF", "2020-01-25"),
                Sailing("AF", "2020-01-31"),
                Sailing("AL", "2020-02-09")
        )
    }
}