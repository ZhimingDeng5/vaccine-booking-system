package com.example.Flying_Tiger.Class;
import com.example.Flying_Tiger.Mapper.RecipientMapper;

import java.sql.Date;
public class Recipient extends User {
    private String name;
    private Date dateBirth;
    // when not booked， hcpID = 0
    private boolean suitable = false;
    private boolean injected = false;
    public Recipient(long ID, String password, String name, Date dateBirth, boolean suitable, boolean injected){
        super(ID, password);
        this.name = name;
        this.dateBirth = dateBirth;
        this.suitable = suitable;
        this.injected = injected;
    }
    public Recipient(long ID, String password, String name, Date dateBirth){
        super(ID, password);
        this.name = name;
        this.dateBirth = dateBirth;
        this.suitable = false;
        this.injected = false;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Date getBirth(){
        return dateBirth;
    }
    public void setBirth(Date dateBirth){
        this.dateBirth = dateBirth;
    }
    public void setSuitable(boolean suitable){
        this.suitable = suitable;
    }
    public boolean getSuitable(){return suitable;}
    public void setInjected(boolean injected){
        this.injected = injected;
    }
    public boolean getInjected(){return injected;}
    public static RecipientMapper getMapper ()
    {
        return RecipientMapper.getInstance();
    }

}
