package org.example;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {

    private JPanel ContentPanel;
    private CardLayout screenManager;
    private JTable table;
    private DefaultTableModel menuModel, orderModel;
    private boolean flag;

    public GUI(boolean CallerisAdmin) {
        setTitle(CallerisAdmin ? "Admin Interface" : "Customer Interface");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.flag = CallerisAdmin;

        screenManager = new CardLayout();
        ContentPanel = new JPanel(screenManager);

        JPanel menuPanel = MenuView();
        ContentPanel.add(menuPanel, "Menu");

        if (flag) {
            JPanel ordersPanel = OrdersView();
            ContentPanel.add(ordersPanel, "Orders");
        }

        add(ContentPanel);

        importMenuItems();

        if (flag) {
            importPendingOrders();
        }

    }
    private JPanel OrdersView() {
        JPanel OrdersP = new JPanel();
        OrdersP.setLayout(new BorderLayout());


        orderModel = new DefaultTableModel(new Object[] { "CUSTOMER", "ITEM", "QUANTITY", "TOTAL", "STATUS" }, 0);
        table = new JTable(orderModel);
        JScrollPane ordersScrollPane = new JScrollPane(table);
        OrdersP.add(ordersScrollPane, BorderLayout.CENTER);

        JButton previous = new JButton("Menu");
        previous.addActionListener(e -> screenManager.show(ContentPanel, "Menu"));
        OrdersP.add(previous, BorderLayout.SOUTH);

        return OrdersP;
    }

    private JPanel MenuView() {
        JPanel MenuP = new JPanel();
        MenuP.setLayout(new BorderLayout());

        menuModel = new DefaultTableModel(new Object[] { "TYPE", "ITEM", "PRICE", "STATUS" }, 0);
        table = new JTable(menuModel);
        JScrollPane menuScrollPane = new JScrollPane(table);
        MenuP.add(menuScrollPane, BorderLayout.CENTER);

        if (flag) { //add others buttons for admin view
            JButton ordersButton = new JButton("Pending Orders");
            ordersButton.addActionListener(e -> screenManager.show(ContentPanel, "Orders"));
            MenuP.add(ordersButton, BorderLayout.SOUTH);
        }

        return MenuP;
    }

    private void importMenuItems() {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("menu.txt")))) {
            while (true) {
                String type = in.readUTF();
                String name = in.readUTF();
                String price = in.readUTF();
                String status = in.readUTF();
                menuModel.addRow(new Object[]{type, name, price, status});
            }
        } catch (EOFException e) {
            // End of file reached, no more menu items
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importPendingOrders() {
        File file = new File("orders.txt");
        if (!file.exists() || file.length() == 0) {
            return;
        }

        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
          //  boolean hasOrders = false;

            while (true) {
                String customerType = in.readUTF();
                String cname = in.readUTF();
                String foodname = in.readUTF();
                int quantity = in.readInt();
                double total = in.readDouble();
                String address = in.readUTF();
                String request = in.readUTF();
                String status = in.readUTF();

                if (status.equals("PENDING")) {
                    orderModel.addRow(new Object[]{customerType + " - " + cname, foodname, quantity, total, status});
                }
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (orderModel.getRowCount() == 0) {
            System.out.println("No valid pending orders found in orders.txt.");
        }
    }
}
