package Logic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Company extends Organization {
    private final ArrayList<Organization> organizations;
    private JFrame frame;

    public Company() {
        super("","",1);
        organizations = new ArrayList<>();
    }

    public void addAirline(String name, String type, int year, String type_technical) {
        organizations.add(new AirLine(name, type, year, type_technical));
    }

    public void addShipBuilding(String name, String type, int year, int ship_number) {
        organizations.add(new ShipBuilding(name, type, year, ship_number));
    }

    public void addInsurance(String name, String type, int year, int employees) {
        organizations.add(new Insurance(name, type, year, employees));
    }

    public Organization find(String name, String type) {
        for (Organization organization : organizations) {
            if (organization.equals(name, type)) {
                return organization;
            }
        }
        return null;
    }

    public void clear() {
        organizations.clear();
    }

    public boolean remove(String name, String type) {
        Organization organization = find(name, type);
        if (organization != null) {
            organizations.remove(organization);
            return true;
        }
        return false;
    }


    public void printAll() {
        //System.out.println('\n');
        if (organizations.isEmpty()) {
            System.out.println("Список пуст");
        }
        for (Organization organization : organizations) {
            organization.printInfo();
        }
        System.out.println("\n");
    }

    @Override
    public void printInfo() {

    }

    @Override
    public String getSpecial() {
        return null;
    }
}

