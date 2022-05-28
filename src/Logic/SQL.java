package Logic;

import org.sqlite.JDBC;

import java.sql.*;

public class SQL {

    private static final String CONNECTION_ADDRESS_MASK = "jdbc:sqlite:./";
    private Connection connection;
    private Company data;
    public static final String[] TABLE_HEADER = new String[]{"ID", "Имя", "Тип", "Год основания", "Специальное поле", "Тип компании"};
    private Organization organization;
    private String name;

    public SQL() {
        data = new Company();
        connection = null;
    }

    public String getName() {
        return name;
    }

    public String connect(String name) {
        this.name = name;
        String result;
        try {
            DriverManager.registerDriver(new JDBC());
            connection = DriverManager.getConnection(CONNECTION_ADDRESS_MASK + name + ".db");
            create();
            result = "База данных успешно открыта!";
        } catch (SQLException exception) {
            result = exception.getMessage();
        }

        return result;
    }

    public Connection getConnection() {
        return connection;
    }


    private void create() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS 'company' (" +
                    "'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "'name' VARCHAR(50) NOT NULL, " +
                    "'type' VARCHAR(50) NOT NULL, " +
                    "'year' INTEGER NOT NULL, " +
                    "'special' VARCHAR(50) NOT NULL, " +
                    "'company_type' VARCHAR(50));");
        } catch (SQLException exception) {

        }
    }

    public void addData(Organization company, String company_type) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("INSERT INTO company ('name', 'type', 'year', 'special', 'company_type')" +
                    " VALUES ('%s', '%s', '%d', '%s', '%s');", company.getName(), company.getType(), company.getYear(), company.getSpecial(), company_type));
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }


    public void executeData() {
        data.clear();
        try (Statement statement = connection.createStatement()) {
            ResultSet exeResult = statement.executeQuery("SELECT * FROM company;");
            while (exeResult.next()) {
                if (exeResult.getString("company_type").equals(Enum.AirLine)) {
                    organization = new AirLine(exeResult.getInt("id"), exeResult.getString("name"), exeResult.getString("type"), exeResult.getInt("year"), exeResult.getString("special"));
                } else if (exeResult.getString("company_type").equals(Enum.Insurance)) {
                    organization = new Insurance(exeResult.getInt("id"), exeResult.getString("name"), exeResult.getString("type"), exeResult.getInt("year"), exeResult.getInt("special"));
                } else if (exeResult.getString("company_type").equals(Enum.ShipBuilding)) {
                    organization = new ShipBuilding(exeResult.getInt("id"), exeResult.getString("name"), exeResult.getString("type"), exeResult.getInt("year"), exeResult.getInt("special"));
                }
                data.addOrganization(organization);
            }

        } catch (SQLException | NullPointerException exception) {

        }
    }

    public Company executeFilterData(String s, int code) {
        Company a = new Company();
        if (s != null && !s.equals("")) {
            switch (code) {
                case 0:
                    a = data.findid(Integer.parseInt(s));
                    break;
                case 1:
                    a = data.findname(s);
                    break;
                case 2:
                    a = data.findType(s);
                    break;
                case 3:
                    a = data.findYear(Integer.parseInt(s));
                    break;
                case 4:
                    a = data.findCompanytype(s);
                    break;
            }

        }
        return a;
    }


    public String removeData(int id) {
        String result;
        try (Statement statement = connection.createStatement()) {
            ResultSet executionResult = statement.executeQuery(String.format("SELECT id FROM company WHERE id = %d;", id));
            if (executionResult.next()) {
                statement.execute(String.format("DELETE FROM company WHERE id = %d;", id));
                statement.execute(String.format("UPDATE company SET rowid = rowid -1 WHERE rowid > %d;", id));
                statement.execute(" UPDATE sqlite_sequence SET seq = seq - 1 WHERE name = \"company\"");
                result = "Информация успешно удалена!";
            } else {
                result = "Информация не найдена!";
            }
        } catch (SQLException exception) {
            result = exception.getMessage();
        }

        return result;
    }


    public void clear() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM company;");
            statement.execute("delete from sqlite_sequence where name='company';");
        } catch (SQLException exception) {

        }
    }

    public Company getData() {
        return this.data;
    }

}
