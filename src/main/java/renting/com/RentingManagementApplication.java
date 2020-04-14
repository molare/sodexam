package renting.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import renting.com.service.EmailService;

import java.io.IOException;

@SpringBootApplication
public class RentingManagementApplication {
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(RentingManagementApplication.class, args);
		try {
			openHomePages();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private static void openHomePages() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8090/login");
	}
}
