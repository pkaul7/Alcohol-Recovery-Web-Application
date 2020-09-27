
  
package  com.gatech.cs6440.alcoholrecovery.controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gatech.cs6440.alcoholrecovery.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;



@RestController
public class ErrController implements ErrorController {

	
	private static final String PATH = "/error";
	
	@Autowired
  	private Message message;
	

	
	

	@RequestMapping(value = "/error")
	public String handleError() {
      		return message.get();
  	}
  
	@Override
	public String getErrorPath() {
		return PATH;
	}
}

