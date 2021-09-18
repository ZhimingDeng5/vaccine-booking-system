package com.example.Flying_Tiger;

public class HealthCareProvider extends User {
    private String name;
    private String type;
    private int postcode;
    private Timeslot[] timeslots;
    public HealthCareProvider(long ID, String password, String name, String type, int postcode, Timeslot[] timeslots){
        super(ID, password);
        this.name = name;
        this.type = type;
        this.postcode = postcode;
        this.timeslots = timeslots;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public int getPost(){
        return postcode;
    }
    public void setPost(int postcode){
        this.postcode = postcode;
    }
    public Timeslot[] getTimeslots(){return this.timeslots;}



}
