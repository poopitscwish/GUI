package Logic;

import java.sql.Struct;

public class AirLine extends Organization implements Money {
    private String type_technical;

    public AirLine(int ID, String name, String type, int year, String type_technical) {
        super(ID,name, type, year,Enum.AirLine);
        setTechnical(type_technical);
    }

    public void setTechnical(String type_technical) {
        this.type_technical = type_technical;
    }

    @Override
    public void Bankruptcy()
    {
        type_technical = "-";
    }


    @Override
    public String[] printInfo() {
        StringBuilder info = new StringBuilder();
        info.append(ID).append(",").
                append(name).append(",")
                .append(type).append(",")
                .append(year).append(",")
                .append(type_technical).append(",")
                .append(company_type);
        return info.toString().split(",");
    }

    @Override
    public String getSpecial() {
        return type_technical;
    }
}

