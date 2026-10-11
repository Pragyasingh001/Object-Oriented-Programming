package org.example;
import java.util.*;

public class User {
    String email;
    String password;
    String name;
    String ctype;

    User(String email, String password, String name, String ctype){
        this.email = email;
        this.password = password;
        this.name = name;
        this.ctype = ctype;

    }

    User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getCtype(){
        return ctype;
    }

    public void setCtype(String s){
        this.ctype = s;
    }



}
