package co.edu.comfanorte.splitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class SplitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitterApplication.class, args);
	}

}
