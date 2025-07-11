package org.example.Copy;

import java.io.Serializable;

public class Address implements Cloneable{
    private String city;
    public Address(String city)
    {
        this.city = city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    public String getCity()
    {
        return city;
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone(); // 浅拷贝
    }
}
