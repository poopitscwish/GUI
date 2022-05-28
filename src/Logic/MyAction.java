package Logic;

import GUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyAction extends JFrame {

    private MainWindow window;
    private String[] filter = {"ID", "Имя", "Тип компании", "Год", "Организация"};
    private JComboBox comboBox = new JComboBox(filter);

    public MyAction(MainWindow window) {
        this.window = window;
    }

    public static Action exitAction = new AbstractAction("Exit") {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    public Action CompanyCreate = new AbstractAction("Добавить") {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CompanyList(window);
        }
    };


    public Action Database = new AbstractAction("Создать/открыть") {
        @Override
        public void actionPerformed(ActionEvent e) {
            String dataBaseName = JOptionPane.showInputDialog(window,
                    new String[]{"Введите название группы\nЕсли группы не существует, она будет создана автоматически"}, "Создание/Открытие", JOptionPane.PLAIN_MESSAGE);
            if (dataBaseName != null && !dataBaseName.equals("")) {
                JOptionPane.showMessageDialog(window, window.getSQL().connect(dataBaseName), "Внимание!", JOptionPane.INFORMATION_MESSAGE);
            }
            window.updateTable(window.getSQL());
        }
    };

    public Action Filter = new AbstractAction("Ввести ключ фильтрации") {
        @Override
        public void actionPerformed(ActionEvent e ) {
            JFrame frame = new JFrame();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            MainWindow.centeringFrame(250, 120, frame);
            Container container = frame.getContentPane();
            container.setLayout(null);
            comboBox.setBounds(0, 0, 100, 25);
            container.add(comboBox);
            JTextField key = new JTextField();
            key.setBounds(105, 0, 125, 25);
            container.add(key);
            JButton button = new JButton("Найти");
            button.setBounds(125,50, 100,25);
            container.add(button);
            button.addActionListener(a -> {
               window.FilterData(window.getSQL().executeFilterData(key.getText(), comboBox.getSelectedIndex()));
            });
        }
    };


    public static void AddCompany(int code, JFrame frame, MainWindow window) {
        switch (code) {
            case 0:
                new NewCompanyWindow(code, "Авиакомпания", "Тип техники", frame, window);
                break;
            case 1:
                new NewCompanyWindow(code, "Страховая компания", "Количество сотрудников", frame, window);
                break;
            case 2:
                new NewCompanyWindow(code, "Судостроительная компания", "Количество судов", frame, window);
                break;
        }
    }
}

