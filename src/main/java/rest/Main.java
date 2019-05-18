package rest;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Class to launch application. Launches spring application and sends HTTP request with string JSON.
 * @author: Ivan Mykolenko
 * @email: vtuse@mail.ru
 */

@SpringBootApplication
public class Main {

	private static String url = "http://localhost:8080/sortcompanies";
	private static String json = "[{ \"custNo\": 20, \"name\": \"British Airways\", \"country\": \"United Kingdom\" }, { \"custNo\": 5, \"name\": \"AIMIA\", \"country\": \"Canada\" }, {\"custNo\": 18,\"name\": \"VECI\",\"country\": \"Spain\" }]";

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		sendRequest();
	}

	/**
	 * Sends JSON string by POST
	 * 
	 * @return void
	 */
	private static void sendRequest() {
		// Assembling request
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Accept", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(json, headers);

		// Getting class type of the list we expect to be returned by the server.
		ParameterizedTypeReference<List<Company>> type = new ParameterizedTypeReference<List<Company>>() {
		};

		// Sending request
		ResponseEntity<List<Company>> response = restTemplate.exchange(url, HttpMethod.POST, entity, type);

		// If not OK
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("Faield with status code: " + response.getStatusCode());
		}

		// Printing results
		printJSON(response.getBody());
	}

	/**
	 * Sends JSON string by POST
	 * 
	 * @param response a list of Company objects returned by server
	 * @return void
	 */
	private static void printJSON(List<Company> response) {
		/* ////////////Printing Raw JSON String//////////////// */
		ObjectMapper objectMapper = new ObjectMapper();
		Company[] co = null;
		int i = 0;
		try {
			co = objectMapper.readValue(json, Company[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n\t\t******Request:");
		for (; i < co.length; i++) {
			System.out.println(co[i].toString());
		}
		System.out.println("\n\t\t******Response:");
		/* /////////Printing JSON returned by server//////////// */
		i = 0;
		for (; i < response.size(); i++) {
			System.out.println(response.get(i).toString());
		}
	}

}