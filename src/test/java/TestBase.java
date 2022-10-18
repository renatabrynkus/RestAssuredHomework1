
import configuration.ApplicationProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TestBase {

    private static ApplicationProperties applicationProperties;
    private static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeAll
    static void beforeAll() {
        applicationProperties = ApplicationProperties.getInstance();
    }

    public static Map<String, String> getHeadersOrParams(String type) {
        Map<String, String> requestValues = new HashMap<>();
        switch (type) {
            case "headers" -> {
                requestValues.put("name", System.getProperty("city"));
                requestValues.put("country", System.getProperty("country"));
            }
            case "params" -> {
                requestValues.put("appid", System.getProperty("appId"));
                requestValues.put("id", System.getProperty("id"));
            }
            default -> logger.info("-----> You can add header or parameters <-----");
        }
        return requestValues;
    }

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("baseUri"))
                .addParams(getHeadersOrParams("params"))
                .addHeaders(getHeadersOrParams("headers"))
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

