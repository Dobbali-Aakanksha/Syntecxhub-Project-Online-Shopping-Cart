import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ShoppingCart {

    JFrame frame;
    JComboBox<String> itemBox;
    JTextField quantityField;
    JTextArea cartArea;
    JButton addBtn, totalBtn, clearBtn;

    ArrayList<String> cartItems = new ArrayList<>();
    HashMap<String, Double> priceMap = new HashMap<>();
    HashMap<String, Integer> quantityMap = new HashMap<>();

    public ShoppingCart() {

        // Prices
        priceMap.put("Apple", 50.0);
        priceMap.put("Banana", 20.0);
        priceMap.put("Milk", 30.0);
        priceMap.put("Bread", 40.0);
        priceMap.put("Eggs", 10.0);

        frame = new JFrame("Online Shopping Cart");
        frame.setSize(450, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel itemLabel = new JLabel("Select Item:");
        itemLabel.setBounds(30, 30, 100, 25);
        frame.add(itemLabel);

        itemBox = new JComboBox<>(priceMap.keySet().toArray(new String[0]));
        itemBox.setBounds(150, 30, 150, 25);
        frame.add(itemBox);

        JLabel qtyLabel = new JLabel("Quantity:");
        qtyLabel.setBounds(30, 70, 100, 25);
        frame.add(qtyLabel);

        quantityField = new JTextField();
        quantityField.setBounds(150, 70, 150, 25);
        frame.add(quantityField);

        addBtn = new JButton("Add to Cart");
        addBtn.setBounds(30, 110, 120, 30);
        frame.add(addBtn);

        totalBtn = new JButton("Total Price");
        totalBtn.setBounds(170, 110, 120, 30);
        frame.add(totalBtn);

        clearBtn = new JButton("Clear Cart");
        clearBtn.setBounds(310, 110, 100, 30);
        frame.add(clearBtn);

        cartArea = new JTextArea();
        cartArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(cartArea);
        scroll.setBounds(30, 160, 380, 180);
        frame.add(scroll);

        // Button Actions
        addBtn.addActionListener(e -> addItem());
        totalBtn.addActionListener(e -> calculateTotal());
        clearBtn.addActionListener(e -> clearCart());

        frame.setVisible(true);
    }

    void addItem() {
        String item = (String) itemBox.getSelectedItem();
        int qty;

        try {
            qty = Integer.parseInt(quantityField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Enter valid quantity");
            return;
        }

        if (cartItems.contains(item)) {
            quantityMap.put(item, quantityMap.get(item) + qty);
        } else {
            cartItems.add(item);
            quantityMap.put(item, qty);
        }

        updateCartView();
        quantityField.setText("");
    }

    void updateCartView() {
        cartArea.setText("");
        for (String item : cartItems) {
            cartArea.append(
                item + " | ₹" + priceMap.get(item) +
                " | Qty: " + quantityMap.get(item) + "\n"
            );
        }
    }

    void calculateTotal() {
        double total = 0;
        for (String item : cartItems) {
            total += priceMap.get(item) * quantityMap.get(item);
        }
        JOptionPane.showMessageDialog(frame, "Total Amount: ₹" + total);
    }

    void clearCart() {
        cartItems.clear();
        quantityMap.clear();
        cartArea.setText("");
    }

    public static void main(String[] args) {
        new ShoppingCart();
    }
}