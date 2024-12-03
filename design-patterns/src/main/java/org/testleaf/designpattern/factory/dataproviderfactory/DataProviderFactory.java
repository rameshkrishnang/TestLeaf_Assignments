package org.testleaf.designpattern.factory.dataproviderfactory;

public class DataProviderFactory {

    public static TestDataProvider getDataProvider(DataSourceType sourceType) {
        switch (sourceType) {
            case EXCEL:
                return new ExcelDataProvider();
            case FAKER:
                return new FakerDataProvider();
            case DATABASE:
                return new DatabaseDataProvider();
            case API:
                return new APIDataProvider();
            case HARDCODED:
                return new HardcodedDataProvider();
            default:
                throw new IllegalArgumentException("Invalid data source type");
        }
    }
}