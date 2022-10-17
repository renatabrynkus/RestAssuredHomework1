package configuration;

import models.Base;
import models.LocationModel;

import java.util.List;
import java.util.Map;

public class ApplicationProperties {

    YamlReader yamlReader = new YamlReader();
    private Base base;
    private List<LocationModel> listOfLocations;

    public ApplicationProperties() {
        setLocationProperties();
        setBaseProperties();
    }

    public static ApplicationProperties getInstance() {
        return ApplicationPropertiesSingleton.INSTANCE;
    }

    private void setLocationProperties() {
        listOfLocations = yamlReader.getConfig().getLocation().getListOfLocations();
        boolean foundActiveEnvironment = false;
        for (LocationModel locationModel : listOfLocations) {
            if (locationModel.isActive()) {
                foundActiveEnvironment = true;
                Map<String, Object> locationModelProperties = locationModel.getProperties();
                for (Map.Entry entry : locationModelProperties.entrySet()) {
                    System.setProperty(entry.getKey().toString(), entry.getValue().toString());
                }
                break;
            }
        }
    }

    private void setBaseProperties() {
        base = yamlReader.getConfig().getBase();
        Map<String, Object> baseProperties = base.getBaseProperties();
        for (Map.Entry entry : baseProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    private static class ApplicationPropertiesSingleton {
        private static final ApplicationProperties INSTANCE = new ApplicationProperties();
    }
}
