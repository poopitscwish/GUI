package GUI;

import Logic.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {
    private static final String[] groupMenuItems = new String[]{"Компания"};
    private static final String[] editMenuItems = new String[]{"Редактировать", "Добавить", "Удалить", "Очистить базу"};
    private static final String[] filterMenuItems = new String[]{"Фильтры", "Ввести ключ" ,"Сбросить"};
    private static final String[] hotKeys = new String[]{"O", "I", "R", "T", "E"};
    private static final Color elementsColor = new Color(231, 232, 232);
    private static final Color windowColor = new Color(175, 175, 175);
    private static final Font regularFont = new Font("Times New Roman", Font.PLAIN, 14);
    private static int menuItemIndex;
    private MyAction myAction;
    private static JTable groupTable;
    private SQL data = new SQL();
    private DefaultTableModel defaultTableModel = new DefaultTableModel(0, 6);


    public MainWindow(String header) {
        super(header);
        myAction = new MyAction(this);
        menuItemIndex = 0;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centeringFrame(1300, 800, this);
        JMenu groupMenu = createMenuItem(groupMenuItems);

        groupMenu.add(myAction.Database);
        groupMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke(hotKeys[0].charAt(0), KeyEvent.CTRL_DOWN_MASK));

        JMenu editMenu = createMenuItem(editMenuItems);
        editMenu.getItem(0).addActionListener(myAction.CompanyCreate);
        editMenu.getItem(1).addActionListener(removeWorker);
        editMenu.getItem(2).addActionListener(clear);

        editMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke(hotKeys[1].charAt(0), KeyEvent.CTRL_DOWN_MASK));
        editMenu.getItem(1).setAccelerator(KeyStroke.getKeyStroke(hotKeys[2].charAt(0), KeyEvent.CTRL_DOWN_MASK));
        editMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke(hotKeys[3].charAt(0), KeyEvent.CTRL_DOWN_MASK));

        JMenu filterMenu = createMenuItem(filterMenuItems);
        filterMenu.getItem(0).addActionListener(myAction.Filter);
        filterMenu.getItem(1).addActionListener(a-> updateTable(data));


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
        groupTable.setShowGrid(true);
        groupTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        groupTable.setPreferredSize(new Dimension(1300, 800));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(groupTable);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        DefaultTableCellRenderer stringCellRenderer = (DefaultTableCellRenderer) groupTable.getDefaultRenderer(String.class);
        stringCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        stringCellRenderer.setVerticalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer numCellRenderer = (DefaultTableCellRenderer) groupTable.getDefaultRenderer(Integer.class);
        numCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        numCellRenderer.setVerticalAlignment(SwingConstants.CENTER);

        defaultTableModel.addRow(SQL.TABLE_HEADER);
        updateTable(data);

        Container container = getContentPane();
        container.setBackground(windowColor);
        container.add(menuBar, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }


    public void updateTableModel(Company company) {
        while (defaultTableModel.getRowCount() > 1)
            defaultTableModel.removeRow(1);

        for (Organization organization : company.getList()) {
            defaultTableModel.addRow(organization.printInfo());
        }
    }

    public SQL getSQL() {
        return data;
    }

    public String getName() {
        return data.getName();
    }

    private JMenu createMenuItem(String[] menuItems) {
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


    public void FilterData(Company data){
        updateTableModel(data);
        groupTable.setModel(defaultTableModel);
    }

    public void updateTable(SQL data) {
        data.executeData();
        updateTableModel(data.getData());
        groupTable.setModel(defaultTableModel);
    }


    public static void centeringFrame(int sizeWidth, int sizeHeight, JFrame frame) {
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (s.width - sizeWidth) / 2;
        int Y = (s.height - sizeHeight) / 2;
        frame.setBounds(X, Y, sizeWidth, sizeHeight);
    }



    Action clear = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            data.clear();
            updateTable(data);
        }
    };

    Action removeWorker = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = groupTable.getSelectedRow();
            if (row == -1 || row == 0) {
                JOptionPane.showMessageDialog(null,
                        "Вы не выбрали запись",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int choose = JOptionPane.showConfirmDialog(
                        null,
                        "Вы действительно хотите удалить запись?",
                        "Подтверждение",
                        JOptionPane.YES_NO_OPTION);
                if (choose == JOptionPane.NO_OPTION) {
                    return;
                }
            }

            data.removeData(row);
            updateTable(data);
        }
    };

}




