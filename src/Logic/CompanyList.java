package Logic;

import javax.swing.*;


import GUI.MainWindow;

public class CompanyList extends JFrame {

    private JButton AirLine = new JButton("Авиакомпания");
    private JButton Insurance = new JButton("Страховая компания");
    private JButton ShipBuilding = new JButton("Судостроительная компания");


    public CompanyList(MainWindow window) {
        super("Выбор компании");
        MainWindow.centeringFrame(220, 160, this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setAlwaysOnTop(true);
        AirLine.setBounds(0, 0, 220, 40);
        Insurance.setBounds(0, 40, 220, 40);
        ShipBuilding.setBounds(0, 80, 220, 40);
        add(AirLine);
        add(Insurance);
        add(ShipBuilding);

        AirLine.addActionListener(e -> MyAction.AddCompany(0, CompanyList.this, window));
        Insurance.addActionListener(e -> MyAction.AddCompany(1, CompanyList.this, window));
        ShipBuilding.addActionListener(e -> MyAction.AddCompany(2, CompanyList.this, window));
    }


}
