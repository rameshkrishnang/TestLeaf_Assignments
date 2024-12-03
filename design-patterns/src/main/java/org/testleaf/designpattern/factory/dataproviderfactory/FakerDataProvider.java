package org.testleaf.designpattern.factory.dataproviderfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FakerDataProvider implements TestDataProvider {

    private static final Logger log = Logger.getLogger(FakerDataProvider.class.getName());

    @Override
    public Map<String, String> getTestData() {
        log.info("Faker Data Provider");
        Map<String, String> data = new HashMap<>();
        data.put("username", "student");
        data.put("password", "Password123");
        return data;
    }

}
