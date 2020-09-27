package com.gatech.cs6440.alcoholrecovery.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Tracking {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "dateTime", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;
    private double alcohol;
    private double water;
    private Integer userId;

    public Tracking(LocalDateTime dateTime, double alcohol, double water, Integer userId) {
       this.dateTime =  dateTime;
       this.alcohol=alcohol;
       this.water = water;
       this.userId = userId;
    }

    @PrePersist
    protected void onCreate()
    {
        if(dateTime==null) {
            dateTime = LocalDateTime.now();
        }
    }


    public Tracking(){

    }


    public String getDay(){
        return ""+this.dateTime.getDayOfMonth();
    }

    public String getYear(){
        return this.dateTime.getYear() +"";
    }

    public String getMonth(){
        return this.dateTime.getMonthValue()+"";
    }
;
}
