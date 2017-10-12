package base

import scala.concurrent.duration._

import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Scenario1 extends Simulation {

    val conf = ConfigFactory.load()
    val baseURL = conf.getString("application.baseURL")

    println("Starting scenario1 at " + baseURL)

	val httpProtocol = http
		.baseURL(baseURL)
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:56.0) Gecko/20100101 Firefox/56.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map("Content-Type" -> "multipart/form-data; boundary=---------------------------49178983219946327681573202106")

	val headers_2 = Map("Content-Type" -> "multipart/form-data; boundary=---------------------------108831513770088621311184984")

	val headers_3 = Map(
		"Accept" -> "application/json",
		"content-type" -> "application/json")

	val headers_4 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"X-Requested-With" -> "XMLHttpRequest")

	val scn = scenario("Scenario1")
		.exec(http("GET /auth/login")
			.get("/auth/login")
			.headers(headers_0))
		.pause(1)
		.exec(http("POST /auth/verify")
			.post("/auth/verify.json")
			.headers(headers_1)
			.body(RawFileBody("Scenario1_0001_request.txt")))
		.pause(2)
		.exec(http("POST /auth/login")
			.post("/auth/login.json")
			.headers(headers_2)
			.body(RawFileBody("Scenario1_0002_request.txt")))
		.pause(1)
		.exec(http("GET /auth/checkSession")
			.get("/auth/checkSession.json")
			.headers(headers_3))
		.pause(1)
		.exec(http("GET /resources")
			.get("/resources.json")
			.headers(headers_4))
		.pause(2)
		.exec(http("GET /auth/logout")
			.get("/auth/logout"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}