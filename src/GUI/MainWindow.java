package GUI;

import Logic.CompanyList;
import Logic.MyAction;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {
    private static final String[] groupMenuItems = new String[]{"Компания"};
    private static final String[] editMenuItems = new String[]{"Редактировать", "Добавить", "Удалить", "Банкротство"};
    private static final String[] filterMenuItems = new String[]{"Фильтры", "ID", "Зачет", "Дифф. зачет", "Экзамен", "Аттестация", "Имя", "Фамилия", "Год рождения", "Сбросить"};
    private static final String[] hotKeys = new String[]{"O", "I", "R", "T", "E"};
    private static final Color elementsColor = new Color(231, 232, 232);
    private static final Color windowColor = new Color(175, 175, 175);
    private static final Font regularFont = new Font("Times New Roman", Font.PLAIN, 14);
    private static int menuItemIndex;
    private static JTable groupTable;

    public MainWindow(String header){
        super(header);

        menuItemIndex = 0;
        centeringFrame(1300,800,this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenu groupMenu = createMenuItem(groupMenuItems);
        groupMenu.add(MyAction.CompanyCreate);
        groupMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke(hotKeys[0].charAt(0), KeyEvent.CTRL_DOWN_MASK));



        JMenu editMenu = createMenuItem(editMenuItems);
        editMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke(hotKeys[1].charAt(0), KeyEvent.CTRL_DOWN_MASK));
        editMenu.getItem(1).setAccelerator(KeyStroke.getKeyStroke(hotKeys[2].charAt(0), KeyEvent.CTRL_DOWN_MASK));
        editMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke(hotKeys[3].charAt(0), KeyEvent.CTRL_DOWN_MASK));


        JMenu filterMenu = createMenuItem(filterMenuItems);
        filterMenu.getItem(8).setAccelerator(KeyStroke.getKeyStroke(hotKeys[4].charAt(0), KeyEvent.CTRL_DOWN_MASK));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(elementsColor);
        menuBar.add(groupMenu);
        menuBar.add(editMenu);
        menuBar.add(filterMenu);

        groupTable = new JTable(0, 0);
        groupTable.setFont(regularFont);
        groupTable.setOpaque(false);
        groupTable.setBackground(Color.WHITE);
        groupTable.setForeground(Color.BLACK);
        groupTable.setRowHeight(30);
        groupTable.setRowHeight(0, 50);
        groupTable.setRowSelectionAllowed(false);
        groupTable.setColumnSelectionAllowed(false);
        groupTable.setCellSelectionEnabled(false);
        groupTable.setShowGrid(true);
        groupTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        groupTable.setPreferredSize(new Dimension(1300, 800));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(groupTable);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        DefaultTableCellRenderer stringCellRenderer = (DefaultTableCellRenderer)groupTable.getDefaultRenderer(String.class);
        stringCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        stringCellRenderer.setVerticalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer numCellRenderer = (DefaultTableCellRenderer)groupTable.getDefaultRenderer(Integer.class);
        numCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        numCellRenderer.setVerticalAlignment(SwingConstants.CENTER);

        Container container = getContentPane();
        container.setBackground(windowColor);
        container.add(menuBar, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }


    private JMenu createMenuItem(String[] menuItems){
        JMenu menu = new JMenu(menuItems[0]);
        menu.setFont(regularFont);

        for (int i = 1; i < menuItems.length; ++i) {
            JMenuItem item = new JMenuItem(menuItems[i]);
            item.setBackground(elementsColor);
            item.setFont(regularFont);

            menu.add(item);
        }

        return menu;
    }


    public static void updateTable(DefaultTableModel updatedData){
        groupTable.setModel(updatedData);
    }


    public static void centeringFrame (int sizeWidth, int sizeHeight, JFrame frame) {
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (s.width - sizeWidth) / 2;
        int Y = (s.height - sizeHeight) / 2;
        frame.setBounds(X, Y, sizeWidth, sizeHeight);
    }
}


