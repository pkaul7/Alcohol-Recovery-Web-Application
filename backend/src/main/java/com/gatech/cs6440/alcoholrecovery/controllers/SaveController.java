package com.gatech.cs6440.alcoholrecovery.controllers;

import com.gatech.cs6440.alcoholrecovery.model.Tracking;
import com.gatech.cs6440.alcoholrecovery.model.TrackingRepository;
import com.gatech.cs6440.alcoholrecovery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping(value = {"/save"})
public class SaveController {
	@Autowired
	private TrackingRepository repository;
	User user;
	
	@RequestMapping("/account")
	public RedirectView receiveArrayOfValues(@RequestParam String[] values) 	
	{
		//get current user
		user = User.getInstance();
	    	RedirectView redirectView = new RedirectView();
		
		//if user is not logged in, make them log in
		if (!user.isLoggedIn()) {
			redirectView.setUrl("https://apps.hdap.gatech.edu/alcoholrecovery2-frontend/login.html");
			return redirectView;
		}  
		
		//save new info
		if (!values[0].equals("empty")) {
			user.setFName(values[0]);
		}
		if (!values[1].equals("empty")) {
			user.setLName(values[1]);
		}
		if (!values[2].equals("empty")) {
			user.setEmail(values[2]);
		}
		if (!values[3].equals("empty")) {
			user.setPass(values[3]);
		}
		if (!values[5].equals("empty")) {
			user.setBDate(values[5]);
		}
		if (!values[6].equals("empty")) {
			user.setHeight(values[6]);
		}
		if (!values[7].equals("empty")) {
			user.setWeight(values[7]);
		}
		
    		redirectView.setUrl("https://apps.hdap.gatech.edu/alcoholrecovery2-frontend/my-account.html");
    		return redirectView;
   	}


	@PostMapping("/trackings")
	public List<Tracking> saveTrackings(@RequestBody List<Tracking> tracking){

		for(Tracking track:tracking) {
			this.repository.save(track);
		}

		return tracking;
	}


	@PostMapping("/tracking")
	public Tracking saveTracking(@RequestBody Tracking track){

		return this.repository.save(track);

	}

}
