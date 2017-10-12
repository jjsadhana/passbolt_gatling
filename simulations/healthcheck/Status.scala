package healthcheck

import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Status extends Simulation {

    val conf = ConfigFactory.load()
    val baseURL = conf.getString("application.baseURL")

    println("Starting performance test at " + baseURL)

    val httpConf = http
        .baseURL(baseURL)
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/52.0")

    object Healthcheck {
        val Status = exec(http("GET Healthcheck Status")
            .get("/healthcheck/status"))
            .pause(1)
    }

    val visitors = scenario("Visitors").exec(Healthcheck.Status)
    setUp(
      visitors.inject(rampUsers(10) over (10 seconds))
    ).protocols(httpConf)
}