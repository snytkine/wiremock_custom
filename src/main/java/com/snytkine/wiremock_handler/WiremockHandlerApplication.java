package com.snytkine.wiremock_handler;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;


public class WiremockHandlerApplication {

	public static void main(String[] args) {
		
		WireMockServer wiremock = new WireMockServer(WireMockConfiguration.options().port(8898));
		wiremock.start();
		System.out.println("~~~~~~~~~~~~~ wiremock started on port " + wiremock.port());

	}

}
