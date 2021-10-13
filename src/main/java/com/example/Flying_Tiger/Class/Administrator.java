package com.example.Flying_Tiger.Class;

import com.example.Flying_Tiger.Mapper.AdministratorMapper;

public class Administrator extends User{
    private String name;
    public Administrator(long ID, String password, String name){
        super(ID, password);
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public static AdministratorMapper getMapper ()
    {
        return new AdministratorMapper();
    }

}
