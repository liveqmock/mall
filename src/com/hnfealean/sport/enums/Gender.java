package com.hnfealean.sport.enums;

public enum Gender {

    MAN {public String getName(){return "M";}},
   
    WOMAN {public String getName(){return "W";}},

    BOTH {public String getName(){return "A";}};
    public abstract String getName();
}
