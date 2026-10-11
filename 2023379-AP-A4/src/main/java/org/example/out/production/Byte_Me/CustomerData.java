package org.example.out.production.Byte_Me;

import org.example.Customer;
import org.example.Menu;
import org.example.OrderManager;

import java.io.*;
import java.util.*;

public class CustomerData {
    Customer customer;

    public CustomerData(Customer customer){
        this.customer = customer;
    }

    public static void LoginRegister(Customer customer) {
        try {
            boolean exists = false; //checking if customer already exists
            File file = new File("user_data.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            List<String> existingUsers = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) { //existing users
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length >= 4 && userData[1].equals(customer.getEmail())) {
                        customer.setCtype(userData[3]);
                        exists = true;
                        break;
                    }
                    existingUsers.add(line);
                }
            }

            if (!exists) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    writer.write(customer.getName() +","+ customer.getEmail() +","+ customer.getPassword() +","+ customer.getCtype());
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Customer FindUser_Login(String email, String password, Menu menu, OrderManager orderManager)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 4 && userData[1].equals(email) && userData[2].equals(password)) {
                    return new Customer(userData[1], userData[2], userData[0], userData[3], menu, orderManager);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    //updating details
    public static void Update_details(Customer customer) {
        try {
            List<String> customers = new ArrayList<>();

            // Read existing customers
            try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData[1].equals(customer.getEmail())) {
                        customers.add(customer.getName() +","+ customer.getEmail() +","+  customer.getPassword() +","+  customer.getCtype());
                    } else {
                        customers.add(line);
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_data.txt"))) {
                for (String customerLine : customers) {
                    writer.write(customerLine + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}