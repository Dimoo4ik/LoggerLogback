
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client implements Comparable<Client> {
    private static ArrayList<Client> setClients = new ArrayList<>();
    private String name; //Mike
    private int age; //23
    private long phoneNumber; //89676400941
    private String regexForPhoneNumber = "8[0-9]{10}";
    private String email; //mura.m.v@mail.ru
    private String regexEmail = "[A-Za-z0-9]+@[A-Za-z0-9]+[.][A-Za-z0-9]+";

    Logger logger = LoggerFactory.getLogger(Client.class);

    public Client(String name, int age, long phoneNumber, String email) {
        this.name = name;
        this.age = age;

        if (Long.toString(phoneNumber).matches(regexForPhoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            phoneNumber = 0;
            logger.error("Ошибка в phoneNumber " + name);
            logger.debug("Ошибка в phoneNumber " + name);
        }
        if (email.matches(regexEmail)) {
            this.email = email;
        } else {
            email = "0";
            logger.error("Ошибка в email "+ name);
            logger.debug("Ошибка в phoneNumber "+ name);
        }
        if (!setClients.contains(this) && phoneNumber != 0 && email != "0") {
            logger.info(name);
            setClients.add(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void printInformationAboutClients() {
        for (Client client : setClients) {
            System.out.println(client);
        }
    }

    public static void removeClient(long phoneNumber) {

        Iterator<Client> clientIterator = setClients.iterator();
        while (clientIterator.hasNext()) {
            Client client = clientIterator.next();
            if (client.getPhoneNumber() == phoneNumber) {
                clientIterator.remove();
            }
        }
    }

    @Override
    public int compareTo(Client o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return age == client.age && phoneNumber == client.phoneNumber && Objects.equals(name, client.name) && Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "Name \"" + getName() + "\"" +
                "\nAge \"" + getAge() + "\"" +
                "\nPhone number \"" + getPhoneNumber() + "\"" +
                "\nEmail \"" + getEmail() + "\".\n";
    }
}
