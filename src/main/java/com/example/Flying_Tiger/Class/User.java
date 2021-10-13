package com.example.Flying_Tiger.Class;

import com.example.Flying_Tiger.Mapper.UserMapper;

public class User {
    private long ID;
    private String password;
    public User(long ID, String password){
        this.ID = ID;
        this.password = password;
    }
    public long getID(){
        return ID;
    }
    public void setID(long ID){
        this.ID = ID;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public static UserMapper getMapper ()
    {
        return new UserMapper("user");
    }

}
