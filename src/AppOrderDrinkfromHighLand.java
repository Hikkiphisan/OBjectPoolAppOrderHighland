import com.sun.security.ntlm.Client;

import java.util.List;

public class AppOrderDrinkfromHighLand {
    public static final int NUM_OF_CLIENT = 8;

    public static void main(String[] args) {

        List<Client> clientfromFile = ReadClientFromFile.readClientFromFile("D:/CodeGym/Module 2/OBjectPoolAppOrderHighland/src/ClienttoHighlandCoffee.txt");

        WaiterPool waiterPool = new WaiterPool();
            for (int i = 0; i < clientfromFile.size(); i++) {
                Client indexclient = clientfromFile.get(i);
                Runnable client = new ClientThread(waiterPool); // Tạo client với thông tin khách hàng
                Thread thread = new Thread(client);
                thread.setName("Khách hàng: " );
                thread.start();
            }

    }
}







//Thread thread = new Thread(client);
//            thread.setName("KhachHang:" + (i + 1)); // Gán tên cho từng luồng khách hàng
//        thread.start();











//        WaiterPool waiterPool = new WaiterPool();
//        for (int i = 0; i <= NUM_OF_CLIENT ; i++) {
//             Runnable client = new ClientThread(waiterPool);
//             Thread thread1 = new Thread(client);
//             Thread thread2 = new Thread(client);
//             Thread thread3 = new Thread(client);
//             Thread thread4 = new Thread(client);
//             Thread thread5 = new Thread(client);
//             Thread thread6 = new Thread(client);
//             Thread thread7 = new Thread(client);
//             Thread thread8 = new Thread(client);
//             thread1.setName("KhachHang -1"); // Gán tên cho luồng
//             thread2.setName("KhachHang -2"); // Gán tên cho luồng
//             thread1.start();
//             thread2.start();
