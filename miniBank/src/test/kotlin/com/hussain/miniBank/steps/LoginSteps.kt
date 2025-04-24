package com.hussain.miniBank.steps

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginSteps {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    private lateinit var username: String
    private lateinit var password: String
    private lateinit var response: ResponseEntity<String>

    @Given("a user with username {string} and password {string}")
    fun a_user_with_username_and_password(username: String, password: String, role: String) {
        this.username = username
        this.password = password
    }

    @When("I send a POST request to {string}")
    fun i_send_a_post_request_to(endpoint: String) {
        val url = "http://localhost:$port$endpoint"
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val requestBody = """
            {
                "username": "$username",
                "password": "$password"
            }
        """.trimIndent()

        val request = HttpEntity(requestBody, headers)
        response = restTemplate.postForEntity(url, request, String::class.java)
    }

    @Then("the response status should be {int}")
    fun the_response_status_should_be(statusCode: Int) {
        assertEquals(statusCode, response.statusCodeValue)
    }

    @And("the response should contain a JWT token")
    fun the_response_should_contain_a_jwt_token() {
        val body = response.body
        assertNotNull(body)
        assertTrue(body!!.contains("token"), "Response should contain a JWT token field")
    }
}