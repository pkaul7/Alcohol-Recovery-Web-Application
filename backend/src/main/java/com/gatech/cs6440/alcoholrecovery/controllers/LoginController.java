package com.gatech.cs6440.alcoholrecovery.controllers;

import com.gatech.cs6440.alcoholrecovery.fhir.FhirService;
import com.gatech.cs6440.alcoholrecovery.model.User;
import com.gatech.cs6440.alcoholrecovery.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = {"/login"})
public class LoginController {
	
	@Autowired
	FhirService _fhirContact;

	@Autowired
	private UserRepository userRepo;

	private String username;
	private String password;
	User user;
	boolean badTry = false;
	
	@RequestMapping("/login")
	public String logIn(@RequestParam String[] values)
	{
		username = values[0];
		password = values[1];
		boolean passGood = true;
		RedirectView redirectView = new RedirectView();
		int id = -1;
		
		//if login is a success, set up new user, get info from database
		User.clearInstance();
		user = userRepo.findByUserName(username);

		if(user==null){
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "unauthorized", new Exception());
		}

		user.setLoggedIn(true);
		User.setCurrentUser(user);
//
//		if (username.equals("demo1")) {
//			id = 140604;
//		} else if (username.equals("demo2")) {
//			id = 140562;
//		} else if (username.equals("demo3")) {
//			id = 140608;
//		} else {
//			passGood = false;
//		}
//
//		//if bad user/password
//		if (!passGood) {
//			throw new ResponseStatusException(
//					HttpStatus.UNAUTHORIZED, "unauthorized", new Exception());
//		}
//
//
//		//set user info from db or FHIR db
//
//		String firstName;
//		String lastName;
//		try {
//			String full = _fhirContact.getName(id+"");
//			String[] splt = full.split("\\s+");
//			firstName=splt[0];
//			lastName= splt[1];
//		}catch(Exception e){
//            firstName = "test";
//            lastName = "test";
//
//		}
//
//
//		user.setUser(id, "bobby@gmail.com", firstName, lastName, username, password, "05/05/91", (int) _fhirContact.getLionicValue("29463-7", id+""), (int) _fhirContact.getLionicValue("8302-2", id+""), true, _fhirContact.getCondition(id+""));
//
		
    	 return "good";
		
	}






	@RequestMapping("/badTry")
	public String logTry() {
		if (badTry) {
			badTry = false;
			return "Sorry, that Username/Password combo didn't work.";
		} else {
			return "";
		}
	}
	
	@RequestMapping("/logout")
	public RedirectView logOut() {
		RedirectView redirectView = new RedirectView();
		User.clearInstance();
		redirectView.setUrl("https://apps.hdap.gatech.edu/alcoholrecovery2-frontend/login.html");
    		return redirectView;
	}

	
	@RequestMapping("/loggedIn")
	public int isLoggedIn() {
		user = User.getInstance();
		if(user.isLoggedIn()){
			return user.getId();
		}else{
			return -1;
		}
	
	}
	
	

}
