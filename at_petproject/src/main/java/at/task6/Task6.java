package at.task6;
//General:
//Install MySQL server (or any SQL like db)
//Make at least two tables (entities from the previous task)
//Make models for those entities (from Task_5)
//Setup Hibernate for those entities
//Check basic CRUD (create, read, update, and delete the BD records using Hibernate)
//Generate a few rows into all tables
import org.hibernate.Session;

import java.util.UUID;

public class Task6 {
    public static void main(String[] args) {

        Address newAddress = createAddress("kittenCity", "meowState");

        Address readAddress = read(newAddress.getId());

        readAddress.setCity("updatedCity");
        update(readAddress);

        Address addressToDelete = createAddress("deleteCity", "deleteState");

        delete(addressToDelete.getId());
    }

    public static Address createAddress(String city, String state) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Address address = new Address(
                UUID.randomUUID().toString().substring(0, 10),
                city,
                state
        );
        session.save(address);
        session.getTransaction().commit();
        session.close();
        System.out.println("Created: " + address);
        return address;
    }

    public static Address read(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = session.get(Address.class, id);
        session.close();
        System.out.println("Read: " + address);
        return address;
    }

    public static void update(Address address) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.update(address);

        session.getTransaction().commit();
        session.close();
    }
    public static void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Address address = session.get(Address.class, id);
        if (address != null) {
            session.delete(address);
            System.out.println("Deleted: " + address);
        }
        session.getTransaction().commit();
        session.close();
    }
}
