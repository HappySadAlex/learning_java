public class Operator implements Employee, Comparable{

    double salary = fixedSalary;

    @Override
    public double getMonthSalary() {
        return salary;
    }

    @Override
    public int compareTo(Object o) {
        Employee operator = (Employee) o;
        return Double.compare(salary, operator.getMonthSalary());
    }

    @Override
    public String toString() {
        return getMonthSalary() + " руб.";
    }
}
