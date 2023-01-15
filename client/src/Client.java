import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 8888);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String order = createOrder();
        dout.writeUTF(order);
        dout.flush();
        String receipt = din.readUTF();
        System.out.println("Receipt Received: " + receipt);
        dout.close();
        s.close();
    }

    public static String createOrder() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter item: ");
        String item = sc.nextLine();
    
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
    
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        sc.nextLine();  // consume newline left-over
    
        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();
    
        System.out.print("Enter customer address: ");
        String customerAddress = sc.nextLine();
    
        String order = item + "," + quantity + "," + price + "," + customerName + "," + customerAddress;
        return order;
    }
}
