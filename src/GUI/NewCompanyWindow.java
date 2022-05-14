package GUI;

import javax.swing.*;
import java.awt.*;

import Logic.Company;
import Logic.SQL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

public class NewCompanyWindow extends JFrame {

    private static ArrayList<JTextField> fields = new ArrayList<>();
    private String[] types = {"ООО", "ЗАО", "ОАО"};
    private SQL database;


    public NewCompanyWindow(int Company_code, String header, String special, JFrame f) {
        super(header);
        database = new SQL();
        database.connect("1");
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

        Company company = new Company();

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
        add_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (Company_code) {
                    case 0:
                        company.addAirline(name.getText(),(String)type.getSelectedItem(),Integer.parseInt(year.getText()),spec.getText());
                        database.addData(company);
                        break;
                }
            }
        });
    }


}
