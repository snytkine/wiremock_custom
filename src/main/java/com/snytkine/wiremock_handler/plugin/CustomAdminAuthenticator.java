package com.snytkine.wiremock_handler.plugin;

import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.security.Authenticator;
import com.github.tomakehurst.wiremock.security.BasicAuthenticator;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "CustomAdminAuthenticator")
public class CustomAdminAuthenticator implements Authenticator {

    private BasicAuthenticator basicAuthenticator;

    public CustomAdminAuthenticator(String username, String password) {
        this.basicAuthenticator = new BasicAuthenticator(username, password);
    }

    @Override
    public boolean authenticate(Request request) {

        var url = request.getUrl();
        var method = request.getMethod().toString();
        log.info("ADMINURL={} method={}", url, method);

        if (url.equals("/shutdown")) {
            log.error("Not allowed to shutdown running server");
            return false;
        }

        if(method.equals("DELETE")){
            log.error("DELETE method not allowed");
            return false;
        }

        if(url.equals("/settings")){
            log.error("Updating global settings via API is not allowed");
            return false;
        }

        if (method.equals("GET")) {
            log.info("GET request is allowed for __admin endpoint {}", url);
            return true;
        }

        return basicAuthenticator.authenticate(request);
    }

}
