import java.util.Random;

public class Manager implements Employee, Comparable{

    public int earned = new Random().nextInt(140000 - 115000) + 115000;
    @Override
    public double getMonthSalary() {
        return fixedSalary + earned * 0.05;
    }

    @Override
    public int compareTo(Object o) {
        Employee manager = (Employee) o;
        return Double.compare(getMonthSalary(), manager.getMonthSalary());
    }

    @Override
    public String toString() {
        return getMonthSalary() + " руб.";
    }
}
