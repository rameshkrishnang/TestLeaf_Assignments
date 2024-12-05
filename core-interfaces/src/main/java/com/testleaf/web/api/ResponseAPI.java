package com.testleaf.web.api;

import java.util.Map;

public interface ResponseAPI {

    /**
     * Retrieves the HTTP status code from the API response.
     *
     * @return the HTTP status code (e.g., 200 for OK, 404 for Not Found).
     */
    int getStatusCode();

    /**
     * Retrieves the status message associated with the API response.
     *
     * @return the HTTP status message (e.g., "OK", "Not Found").
     */
    String getStatusMessage();

    /**
     * Retrieves the body of the API response.
     *
     * @return the response body as a String. This may contain JSON, XML, or plain text depending on the API.
     */
    String getBody();

    /**
     * Retrieves the headers from the API response.
     *
     * @return a Map containing header key-value pairs, where the key is the header name, and the value is the header value.
     */
    Map<String, String> getHeaders();
}
