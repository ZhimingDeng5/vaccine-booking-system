package com.example.Flying_Tiger;

public class Vaccine {
    private long ID;
    private String vacType;
    public Vaccine(long ID, String vacType){
        this.ID = ID;
        this.vacType = vacType;
    }
    public String getType(){
        return vacType;
    }
    public void setType(String type){
        this.vacType = type;
    }

    public static VaccineMapper getMapper ()
    {
        return new VaccineMapper();
    }

}
