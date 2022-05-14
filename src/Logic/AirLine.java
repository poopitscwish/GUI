package Logic;

public class AirLine extends Organization implements Money {
    private String type_technical;

    public AirLine(String name, String type, int year, String type_technical) {
        super(name, type, year);
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
    public void printInfo() {
        System.out.printf("Авиа компания %s \"%s\", созданная в %d году (тип выпускаемой техники: %s)\n", type, name, year, type_technical + "\n");
    }

    @Override
    public String getSpecial() {
        return type_technical;
    }
}

