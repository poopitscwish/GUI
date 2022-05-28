package Logic;


public class ShipBuilding extends Organization implements Money {
    private String ship_number;

    public ShipBuilding(int ID,String name, String type, int year, int ship_number) {
        super(ID,name, type, year,Enum.ShipBuilding);
        setShip_number(ship_number);

    }

    public void setShip_number(int ship_number) {
        this.ship_number = Integer.toString(ship_number);
    }

    public void Bankruptcy()
    {
        ship_number = "";
    }

    @Override
    public String[] printInfo() {
        StringBuilder info = new StringBuilder();
        info.append(ID).append(",").
                append(name).append(",")
                .append(type).append(",")
                .append(year).append(",")
                .append(ship_number).append(",")
                .append(company_type);
        return info.toString().split(",");
    }

    @Override
    public String getSpecial() {
        return ship_number;
    }
}
