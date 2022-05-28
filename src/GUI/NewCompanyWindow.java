package GUI;

import javax.swing.*;
import java.awt.*;

import Logic.*;
import Logic.Enum;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

public class NewCompanyWindow extends JFrame {

    private static ArrayList<JTextField> fields = new ArrayList<>();
    private String[] types = {"ООО", "ЗАО", "ОАО"};
    private SQL database;
    private Organization company;

    public NewCompanyWindow(int Company_code, String header, String special, JFrame f, MainWindow window) {
        super(header);
        database = new SQL();
        if(window.getName() !=null && !window.getName().equals(""))
         database.connect(window.getName());
        f.setVisible(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                f.setVisible(true);
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        MainWindow.centeringFrame(250, 200, this);
        Container container = getContentPane();
        container.setLayout(null);
        setVisible(true);

        JLabel Company_name = new JLabel("Имя компании:");
        Company_name.setBounds(3, 0, 150, 30);

        JLabel Company_type = new JLabel("Тип компании:");
        Company_type.setBounds(3, 30, 150, 30);

        JLabel Company_found = new JLabel("Год основания:");
        Company_found.setBounds(3, 60, 150, 30);

        JLabel Company_special = new JLabel(special + ":");
        Company_special.setBounds(3, 90, 150, 30);


        JTextField name = new JTextField("");
        name.setBounds(120, 5, 100, 25);
        JComboBox type = new JComboBox(types);
        type.setBounds(120, 35, 100, 25);
        JTextField year = new JTextField("");
        year.setBounds(120, 65, 100, 25);
        JTextField spec = new JTextField("");
        spec.setBounds(120, 95, 100, 25);

        JButton add_button = new JButton("Добавить");
        add_button.setBounds(75, 125, 100, 25);

        container.add(Company_name);
        container.add(name);

        container.add(Company_type);
        container.add(type);

        container.add(Company_found);
        container.add(year);

        container.add(Company_special);
        container.add(spec);

        container.add(add_button);

        add_button.addActionListener(e -> {
            try {
                if (database.getConnection() != null) {
                    switch (Company_code) {
                        case 0:
                            company = new AirLine(0, name.getText(), (String) type.getSelectedItem(), Integer.parseInt(year.getText()), spec.getText());
                            database.addData(company, Enum.AirLine);
                            break;
                        case 1:
                            company = new Insurance(0, name.getText(), (String) type.getSelectedItem(), Integer.parseInt(year.getText()), Integer.parseInt(spec.getText()));
                            database.addData(company, Enum.Insurance);
                            break;
                        case 2:
                            company = new ShipBuilding(0, name.getText(), (String) type.getSelectedItem(), Integer.parseInt(year.getText()), Integer.parseInt(spec.getText()));
                            database.addData(company, Enum.ShipBuilding);
                            break;
                    }
                }
                else
                    JOptionPane.showMessageDialog(window, "Вы не открыли базу данных!", "Внимание!", JOptionPane.ERROR_MESSAGE);
                window.updateTable(window.getSQL());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(window, "Введите числовое значение!", "Внимание!", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

