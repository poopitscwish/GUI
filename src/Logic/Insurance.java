package Logic;

public class Insurance extends Organization implements Money {
    private String employees;

    public Insurance(String name, String type, int year, int employees) {
        super(name, type, year);
        setEmployees(employees);
    }

    public void setEmployees(int employees) {
        this.employees = Integer.toString(employees);
    }

    public void Bankruptcy()
    {
        employees = "";
    }

    @Override
    public void printInfo() {
        System.out.print("Страховая компания " + type + " " + name + ", основанная в " + year + " году," + " кол-во сотрудников: " + employees + "\n");
    }

    @Override
    public String getSpecial() {
        return employees;
    }
}

