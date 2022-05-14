package Logic;

import GUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyAction extends JFrame  {


    public static Action exitAction = new AbstractAction("Exit") {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    public static Action CompanyCreate = new AbstractAction("Создать/открыть") {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CompanyList();
        }
    };

    public static void AddCompany(int code, JFrame frame) {
        switch (code){
            case 0:

            new NewCompanyWindow(code,"Авиакомпания","Тип техники", frame);

            break;
        }
    }
}

