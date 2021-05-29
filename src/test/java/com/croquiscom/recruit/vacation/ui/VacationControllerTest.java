package com.croquiscom.recruit.vacation.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VacationControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }
    }

    @Test
    public void test() {
        // given
        Map<String, String> params = new HashMap<>();
        params.put("vacationType", "whole");
        params.put("vacationStartDate", "20210601");
        params.put("vacationEndDate", "20210604");
        params.put("usedDays", "4");
        params.put("comment", "asdf");
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .auth().basic("user", "1234")
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/vacations")
                .then().log().all()
                .extract();
        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

}
