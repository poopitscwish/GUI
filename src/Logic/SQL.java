package Logic;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;

public class SQL {

    private static final String CONNECTION_ADDRESS_MASK = "jdbc:sqlite:./";
    private Connection connection;
    private ArrayList<Company> data;

    public String connect(String name){
        String result;
        try{
            DriverManager.registerDriver(new JDBC());
            connection = DriverManager.getConnection(CONNECTION_ADDRESS_MASK + name + ".db");
            create();
            result = "База данных успешно открыта!";
        }
        catch (SQLException exception){
            result = exception.getMessage();
        }
        catch (NullPointerException exception){
            result = "Название группы не было введено пользователем!";
        }

        return result;
    }

    private void create() throws SQLException{
        try (Statement statement = connection.createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS 'company' (" +
                    "'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "'name' VARCHAR(50) NOT NULL, " +
                    "'type' VARCHAR(50) NOT NULL, " +
                    "'year' INTEGER NOT NULL, " +
                    "'special' VARCHAR(50) NOT NULL, " );
        }
    }

    public void addData(Company company){
        try (Statement statement = connection.createStatement()){
            statement.execute(String.format("INSERT INTO company ('name', 'type', 'year', 'special')" +
                    " VALUES ('%s', '%s', '%d', '%s');", company.getName(),company.getType(),company.getYear(),company.getSpecial()));
        }
        catch (SQLException exception){
            System.out.println(exception.getMessage()+ "index 1");
        }
    }

}
