package at.task6;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Address implements Serializable {
    private String street;
    private String city;
    private String state;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    public Address() {}

    public Address( String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
