import java.io.Serializable;

public class Client implements Serializable {
    private int id;
    private String name;
    private String drink;

    public Client(int id,String name, String drink) {
        this.id = id;
        this.name = name;
        this.drink = drink;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDrink() {
        return drink;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", drink='" + drink + '\'' +
                '}';
    }
}