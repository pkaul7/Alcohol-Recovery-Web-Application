package  com.gatech.cs6440.alcoholrecovery.model;

public class Milestone {

    private static int id = 0;
    private String title;
    private int percentComplete;

    public Milestone(String title, int percentComplete){
        this.id++;
        this.title = title;
        this.percentComplete = percentComplete;
    }

    public void setMilestone(String title, int percentComplete){
        this.title = title;
        this.percentComplete = percentComplete;
    }

    public int getId(){
        return id;
    }

    public int getPercentComplete(){
        return percentComplete;
    }

    public String getTitle(){
        return title;
    }
}
