package com.snytkine.wiremock_handler;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.snytkine.wiremock_handler.plugin.CustomAdminAuthenticator;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "WiremockHandlerApplication")
public class WiremockHandlerApplication {

	private static final String DEFAULT_SERVER_PORT = "8898";

	public static void main(String[] args) {

		String adminUser = System.getenv().get("WIREMOCK_ADMIN_USER");
		String adminPassword = System.getenv().get("WIREMOCK_ADMIN_PASSWORD");

		if (adminUser == null || adminPassword == null) {
			throw new RuntimeException(
					"WIREMOCK_ADMIN_USER and WIREMOCK_ADMIN_PASSWORD environment variables must be set prior to starting this application");
		}
		String wiremockServerPort = System.getenv().get("WIREMOCK_SERVER_PORT");
		if (wiremockServerPort == null) {
			wiremockServerPort = DEFAULT_SERVER_PORT;
		}
		int serverPort = Integer.parseInt(wiremockServerPort);

		var customAdminAuthenticator = new CustomAdminAuthenticator(adminUser, adminPassword);

		WireMockServer wiremock = new WireMockServer(
				WireMockConfiguration.options()
						.port(serverPort)
						.usingFilesUnderDirectory("mocks")
						.adminAuthenticator(customAdminAuthenticator));
		wiremock.start();
		log.info("WireMock started on port {}", wiremock.port());
	}

}
