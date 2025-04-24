package com.hussain.miniBank

import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MiniBankApplicationTests {

	@Test
	fun contextLoads() {
	}

}
