
import configuration.ApplicationProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    private static ApplicationProperties applicationProperties;

    @BeforeAll
    static void beforeAll() {
        applicationProperties = ApplicationProperties.getInstance();
    }

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("baseUri"))
                .addParam("appid", System.getProperty("appId"))
                .addParam("id", System.getProperty("id"))
                .addHeader("name", System.getProperty("city"))
                .addHeader("country", System.getProperty("country"))
                .setContentType(ContentType.JSON)
                .build()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .build()
                .statusCode(200);
    }
}

