package at.task6;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private Boolean isEmployed;
    private Address address;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", isEmployed=" + isEmployed +
                ", address=" + address +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsEmployed() {
        return isEmployed;
    }

    public void setIsEmployed(Boolean isEmployed) {
        this.isEmployed = isEmployed;
    }


    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}
