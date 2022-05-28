package Logic;

public class Insurance extends Organization implements Money {
    private String employees;

    public Insurance(int ID, String name, String type, int year, int employees) {
        super(ID,name, type, year,Enum.Insurance);
        this.employees = Integer.toString(employees);
    }



    public void Bankruptcy()
    {
        employees = "";
    }

    @Override
    public String[] printInfo() {
        StringBuilder info = new StringBuilder();
        info.append(ID).append(",").
                append(name).append(",")
                .append(type).append(",")
                .append(year).append(",")
                .append(employees).append(",")
                .append(company_type);
        return info.toString().split(",");
    }

    @Override
    public String getSpecial() {
        return employees;
    }
}

