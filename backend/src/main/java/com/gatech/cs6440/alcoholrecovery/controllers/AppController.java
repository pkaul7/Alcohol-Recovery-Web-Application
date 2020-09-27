package com.gatech.cs6440.alcoholrecovery.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class AppController {


    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>(
                "application is running",
                HttpStatus.OK);
    }


    @GetMapping("/")
    @ResponseBody
    public String message() {
        return "Its working";
    }
	
    @RequestMapping("/date")
    public String getDate() {
	Format formatter = new SimpleDateFormat("MMMMMMMM dd, yyyy");
        return formatter.format(new Date());
    }
	
    
}
