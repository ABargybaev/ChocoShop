package chocoshop;

public class Supplier {
    private int id;
    private String name;
    private String address;
    private String city;
    private String phone;

    public Supplier(int id, String name, String address, String city, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getPhone() {
        return phone;
    }

    public String toString() {
        return "Supplier [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city;
    }
}
