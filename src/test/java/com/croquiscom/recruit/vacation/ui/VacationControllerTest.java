package com.croquiscom.recruit.vacation.ui;

import com.croquiscom.recruit.vacation.domain.VacationMock;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("vacation controller test")
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

    @DisplayName("create vacation")
    @Test
    public void createVacation() {
        // given
        Map<String, String> params = makeParam();
        // when
        ExtractableResponse<Response> response = createVacation(params);
        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    private Map<String, String> makeParam() {
        Map<String, String> params = new HashMap<>();
        params.put("vacationType", "whole");
        params.put("vacationStartDate", "20210601");
        params.put("vacationEndDate", "20210604");
        params.put("usedDays", "4");
        params.put("comment", "asdf");
        return params;
    }

    private ExtractableResponse<Response> createDummyVacation() {
        return createVacation(makeParam());
    }

    private ExtractableResponse<Response> createVacation(Map<String, String> params) {
        return RestAssured.given().log().all()
                .auth().basic("user", "1234")
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/vacations")
                .then().log().all()
                .extract();
    }

    @DisplayName("get vacations")
    @Test
    public void getVacations() {
        // given
        createDummyVacation();
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .auth().basic("user", "1234")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/vacations")
                .then().log().all()
                .extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        List<Long> actualVacationIds = response.jsonPath().getList(".", VacationMock.class).stream()
                .map(it -> it.getVacationId())
                .collect(Collectors.toList());
        assertThat(actualVacationIds).containsAll(Lists.newArrayList(1L));
    }

    @DisplayName("cancel vacations")
    @Test
    public void cancelVacations() {
        // given
        createDummyVacation();
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .auth().basic("user", "1234")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put("/vacations/cancel/1")
                .then().log().all()
                .extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

}
