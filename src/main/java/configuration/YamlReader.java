package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlReader {
    public static Configuration config;
    public Configuration getConfig() {
        return config;
    }

    public YamlReader () {
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            this.config = objectMapper.readValue(new File("src/main/resources/config.yaml"), Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
