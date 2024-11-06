public class AppOrderDrinkfromHighLand {
    public static final int NUM_OF_CLIENT = 8;
    public static void main ( String[] args) {
        WaiterPool waiterPool = new WaiterPool();
        for (int i = 0; i <= NUM_OF_CLIENT ; i++) {
             Runnable client = new ClientThread(waiterPool);
             Thread thread = new Thread(client);
             thread.start();
        }
    }
}
