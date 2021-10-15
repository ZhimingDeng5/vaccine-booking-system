package com.example.Flying_Tiger.Class;

import com.example.Flying_Tiger.Mapper.VaccineMapper;

public class Vaccine {
    private long ID;
    private String vacType;
    private int version;
    public Vaccine(long ID, String vacType,int version){
        this.ID = ID;
        this.vacType = vacType;
        this.version = version;
    }
    public String getType(){
        return vacType;
    }
    public void setType(String type){
        this.vacType = type;
    }
    public int getVersion(){return this.version;}
    public void setVersion(int version){this.version= version;}
    public long getID(){return this.ID;}
    public static VaccineMapper getMapper ()
    {
        return VaccineMapper.getInstance();
    }

}
