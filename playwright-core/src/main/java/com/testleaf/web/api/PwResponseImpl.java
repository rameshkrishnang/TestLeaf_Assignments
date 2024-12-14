package com.testleaf.web.api;

import com.microsoft.playwright.APIResponse;

import java.util.Map;

public class PwResponseImpl implements ResponseAPI {

    private final APIResponse response;

    public PwResponseImpl(APIResponse response) {
        this.response = response;
    }

    @Override
    public int getStatusCode() {
        return response.status();
    }

    @Override
    public String getStatusMessage() {
        return response.statusText();
    }

    @Override
    public String getBody() {
        return response.text();
    }

    @Override
    public Map<String, String> getHeaders() {
        return response.headers();
    }

}
