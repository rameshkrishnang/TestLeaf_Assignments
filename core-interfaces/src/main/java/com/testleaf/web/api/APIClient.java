package com.testleaf.web.api;

public interface APIClient {

    /**
     * Sends a GET request to the specified API endpoint.
     *
     * @param endPoint the API endpoint to which the GET request is sent.
     * @param token    the authorization token for the API call.
     * @return a ResponseAPI object containing the response from the server.
     */
    ResponseAPI get(String endPoint, String token);

    /**
     * Sends a POST request to the specified API endpoint.
     *
     * @param endPoint the API endpoint to which the POST request is sent.
     * @param token    the authorization token for the API call.
     * @param body     the request body to be sent with the POST request.
     * @return a ResponseAPI object containing the response from the server.
     */
    ResponseAPI post(String endPoint, String token, Object body);

    /**
     * Sends a PUT request to the specified API endpoint.
     *
     * @param endPoint the API endpoint to which the PUT request is sent.
     * @param token    the authorization token for the API call.
     * @param body     the request body to be sent with the PUT request, typically to update existing data.
     * @return a ResponseAPI object containing the response from the server.
     */
    ResponseAPI put(String endPoint, String token, Object body);

    /**
     * Sends a DELETE request to the specified API endpoint.
     *
     * @param endPoint the API endpoint from which a resource should be deleted.
     * @param token    the authorization token for the API call.
     * @return a ResponseAPI object containing the response from the server.
     */
    ResponseAPI delete(String endPoint, String token);
}