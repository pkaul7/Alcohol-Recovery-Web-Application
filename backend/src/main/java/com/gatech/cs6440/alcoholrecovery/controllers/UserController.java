package com.gatech.cs6440.alcoholrecovery.controllers;

import com.gatech.cs6440.alcoholrecovery.model.Tracking;
import com.gatech.cs6440.alcoholrecovery.model.TrackingRepository;
import com.gatech.cs6440.alcoholrecovery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = {"/user"})
public class UserController {
    @Autowired
    private TrackingRepository repository;
	User user;

	
	@RequestMapping("/getName")
	public String getFullName() {
		user = User.getInstance();
    		return user.getFName() + " " + user.getLName();
	}


   @RequestMapping("/drinkData")
   public List<Tracking> getTrackings(@RequestParam(value="id", defaultValue = "-1") int userId){
		if(userId!=-1){
			return repository.findByUserId(userId, Sort.by("dateTime"));
		}else{
			return repository.findByUserId(User.getInstance().getId(), Sort.by("dateTime"));
		}

   }

	@RequestMapping("/lastDrink")
	public Tracking getLastDrink(@RequestParam(value="id", defaultValue = "-1") int userId){
		if(userId!=-1){
			return repository.findLastDrinkByUserId(userId);
		}else{
			return repository.findLastDrinkByUserId(User.getInstance().getId());
		}

	}

	@RequestMapping("/getUName")
	public String getUsername() {
		user = User.getInstance();
		return user.getUName();
	}
	
	@RequestMapping("/getCondition")
	public String getCondition() {
		user = User.getInstance();
		return user.getcondition();
	}
	
	@RequestMapping("/getWeight")
	public int getWeight() {
		user = User.getInstance();
		return user.getWeight();
	}
	
	@RequestMapping("/getHeight")
	public int getHeight() {
		user = User.getInstance();
		return user.getHeight();
	}
	

}
