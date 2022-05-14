package Logic;

public class ShipBuilding extends Organization implements Money {
    private String ship_number;

    public ShipBuilding(String name, String type, int year, int ship_number) {
        super(name, type, year);
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
    public void printInfo() {
        System.out.printf("Судостроительная компания %s \"%s\", созданная в %d году (%d выпущенных кораблей)\n", type, name, year, ship_number + "\n");
    }

    @Override
    public String getSpecial() {
        return ship_number;
    }
}
