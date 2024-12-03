package org.testleaf.designpattern.factory.dataproviderfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class APIDataProvider implements TestDataProvider {

    private static final Logger log = Logger.getLogger(APIDataProvider.class.getName());

    @Override
    public Map<String, String> getTestData() {
        log.info("API Data Provider");
        Map<String, String> data = new HashMap<>();
        data.put("username", "student");
        data.put("password", "Password123");
        return data;
    }
}
