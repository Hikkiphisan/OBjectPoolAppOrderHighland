import com.sun.security.ntlm.Client;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class ClientThread implements Runnable {
    private WaiterPool waiterPool;

    public ClientThread(WaiterPool waiterPool) {
        this.waiterPool = waiterPool;
    }
    public void run() {
        orderaDrink();
    }
    private void orderaDrink() {
        try {
            System.out.println("Khách hàng mới: " + Thread.currentThread().getName());
            WaiterInServer waiter = waiterPool.getWaiter();

            TimeUnit.MILLISECONDS.sleep(randomInt(1000,1500));
            waiterPool.release(waiter);
            System.out.println("Vị khách hàng tên là: " + Thread.currentThread().getName() + " đã được phục vụ!");
        } catch (InterruptedException | WaiterNotFoundException e) {
            System.out.println("Từ chối phục vụ vị khách: " + Thread.currentThread().getName());
        }
    }


    public static int randomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
//    List<Client> client = ReadClientFromFile.readClientFromFile("ClienttoHighlandCoffee.txt");
//        for (int i = 0; i < client.size(); i++) {
//        Client client1 = client.get(i);
//        Runnable client = new ClientThread(client1); // Tạo client với thông tin khách hàng
//    }
}



