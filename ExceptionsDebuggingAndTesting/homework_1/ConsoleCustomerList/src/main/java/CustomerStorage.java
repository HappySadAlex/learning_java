import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if(components.length != 4){ throw new ArrayIndexOutOfBoundsException("Wrong format adding customer. Example:\n" +
                "add Василий Петров vasily.petrov@gmail.com +79215637722");}
        if (!components[INDEX_EMAIL].matches("[a-zA-Z]+[0-9]?@[a-zA-Z]+[0-9]?\\.{1}[a-zA-Z]+[0-9]?")) {
            throw new IllegalArgumentException("Wrong email format");
        }
        if (!components[INDEX_PHONE].matches("[+][0-9]+")) {
            throw new IllegalArgumentException("Wrong phone format");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        if(!name.matches("[а-яА-Я]+\\s[а-яА-Я]+")){ //проверка имени
            throw new IllegalArgumentException("Wrong name format");
        }
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}