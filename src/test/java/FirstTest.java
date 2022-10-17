import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class FirstTest extends TestBase {

    @Test
    void shouldGetCurrentWeatherForSpecificCity() {
        RestAssured.given()
                .spec(getRequestSpec())
                .when()
                .get()
                .then()
                .spec(TestBase.getResponseSpec());
    }
}
