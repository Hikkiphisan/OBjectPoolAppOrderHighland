import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class WaiterPool {
    private static final long EXPIRED_TIME_IN_MILESECOND = 1200;
    private static final long NUMBER_OF_WAITER = 4;

    private final List<WaiterInServer> available = Collections.synchronizedList(new ArrayList<>());
    private final List<WaiterInServer> inUse = Collections.synchronizedList(new ArrayList<>());

    private final AtomicInteger count = new AtomicInteger(0);
    private final AtomicBoolean waiting = new AtomicBoolean(false);

    public synchronized WaiterInServer getWaiter() {
        if (!available.isEmpty()) {
            WaiterInServer waiter = available.remove(0);
            inUse.add(waiter);
            return waiter;
        }
        if (count.get() == NUMBER_OF_WAITER) {
            this.waitingUntilWaiterAvailable();
            return this.getWaiter();
        }
        WaiterInServer waiter = this.createWaiter();
        inUse.add(waiter);
        return waiter;
    }

    public synchronized void release(WaiterInServer waiter)  {
        inUse.remove(waiter);
        available.add(waiter);
        System.out.println(waiter.getNameWaiter() + " đang rảnh.");
    }

  private WaiterInServer createWaiter() {
        waiting(200);
        WaiterInServer waiter = new WaiterInServer("Nhan vien thu " + count.incrementAndGet()); //TRuyen nhan vien boi ban cua HIgland vao
      System.out.println(waiter.getNameWaiter() + "is created");
      return waiter;
    }



  private void waitingUntilWaiterAvailable() {
        if (waiting.get()) {
            waiting.set(false);
            throw new WaiterNotFoundException("Không có nhân viên phục vụ nào có thể chạy bàn");
        }
        waiting.set(true);
        waiting(EXPIRED_TIME_IN_MILESECOND); //1200
  }



  private void waiting(long numberOfSecond) {
        try {
            TimeUnit.MILLISECONDS.sleep(numberOfSecond); //đưa luồng (thread) vào trạng thái ngủ (sleep) trong một khoảng thời gian nhất định, được đo bằng mili giây.
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // gửi tín hiệu ngắt (interrupt) tới chính luồng hiện tại. Khi gọi phương thức này, nó đánh dấu luồng hiện tại là bị "ngắt" (interrupted), nhưng không tự động dừng luồng đó.
        }
  }

}
