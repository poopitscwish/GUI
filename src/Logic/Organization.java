package Logic;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;

public abstract class Organization {
    protected String name;
    protected String type;
    protected int year;
    protected int ID;
    protected String company_type;

    public Organization(int ID,String name, String type, int year, String company_type) {
        setYear(year);
        setName(name);
        setType(type);
        this.ID = ID;
        this.company_type = company_type;
    }

    public String getCompany_type(){return company_type;}

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public int getID(){return ID;}

    public void setID(int a){this.ID = a;}

    public int getYear(){
        return year;
    }

    public abstract String [] printInfo();
    public abstract String getSpecial();



    public boolean equals(String name, String type) {
        return this.name.equals(name) && this.type.equals(type);
    }



}
