import io.restassured.RestAssured;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class FirstTest {

    @ParameterizedTest
    @MethodSource("provideParameters")
    void shouldGetCurrentWeatherForSpecificCity(String city, String country, String id) {
        RestAssured.given()
                .spec(TestBase.getRequestSpec(city, country, id))
                .when()
                .get()
                .then()
                .spec(TestBase.getResponseSpec());
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of("London", "GB", "2643743"),
                Arguments.of("Oxford", "GB", "2640729"),
                Arguments.of("Gdańsk", "PL", "3099434")
        );
    }
}
