import java.io.*;
import java.net.*;

class Server {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(8888);
        while (true) {
            Socket s = ss.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            String order = din.readUTF();
            System.out.println("Order Received: " + order);
            String receipt = interpretOrder(order);
            dout.writeUTF(receipt);
            dout.flush();
            dout.close();
            s.close();
        }
    }

    public static String interpretOrder(String order) {
        String[] orderDetails = order.split(",");
        String item = orderDetails[0];
        int quantity = Integer.parseInt(orderDetails[1]);
        double price = Double.parseDouble(orderDetails[2]);
        String customerName = orderDetails[3];
        String customerAddress = orderDetails[4];

        String receipt = "";
        if (quantity <= 0) {
            receipt = "Invalid quantity";
        } else {
            double totalPrice = quantity * price;
          //  String deliveryDate = calculateDeliveryDate();
            receipt = "Order Confirmed\n"
                    + "Item: " + item + "\n"
                    + "Quantity: " + quantity + "\n"
                    + "Price: $" + price + "\n"
                    + "Total Price: $" + totalPrice + "\n"
                    + "Customer Name: " + customerName + "\n"
                    + "Customer Address: " + customerAddress + "\n";
                    // + "Delivery Date: " + deliveryDate;
        }

        return receipt;
    }
}
