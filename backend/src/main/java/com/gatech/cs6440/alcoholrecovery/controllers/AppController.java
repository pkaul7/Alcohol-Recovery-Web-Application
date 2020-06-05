package  com.gatech.cs6440.alcoholrecovery.controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class AppController {



	@GetMapping("/status")
	public ResponseEntity<String>  status() {
		 return new ResponseEntity<>(
      "application is running", 
      HttpStatus.OK); 
	}
}
