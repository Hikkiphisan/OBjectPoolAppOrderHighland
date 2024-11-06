import com.sun.security.ntlm.Client;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadClientFromFile {
    static List<Client> readClientFromFile(String filename) {
        List<Client> customers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // Đọc các đối tượng từ file và thêm vào danh sách
            while (true) {
                try {
                    customers.add((Client) ois.readObject());
                } catch (EOFException e) {
                    break; // Kết thúc khi không còn dữ liệu
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }
}

