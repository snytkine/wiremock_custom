package com.snytkine.wiremock_handler;
import org.springframework.cloud.contract.wiremock.WireMockSpring;

import com.github.tomakehurst.wiremock.WireMockServer;

//@SpringBootApplication

public class WiremockHandlerApplication {

	public static void main(String[] args) {
		//SpringApplication.run(WiremockHandlerApplication.class, args);
		WireMockServer wiremock = new WireMockServer(WireMockSpring.options().port(8898));
		wiremock.start();
		System.out.println("~~~~~~~~~~~~~ wiremock started on port " + wiremock.port());

	}

}
