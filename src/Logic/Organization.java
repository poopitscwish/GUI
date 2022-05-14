package Logic;

public abstract class Organization<T> {
    protected String name;
    protected String type;
    protected int year;

    public Organization(String name, String type, int year) {
        setYear(year);
        setName(name);
        setType(type);
    }

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

    public int getYear(){
        return year;
    }

    public abstract void printInfo();
    public abstract String getSpecial();


    public boolean equals(String name, String type) {
        return this.name.equals(name) && this.type.equals(type);
    }
}
