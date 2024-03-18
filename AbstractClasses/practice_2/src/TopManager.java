public class TopManager implements Employee, Comparable
{

    Company work;

    public TopManager(Company company) {
        work = company;
    }

    @Override
    public double getMonthSalary() {
        if(work.getIncome() > 10_000_000){
            return fixedSalary * 1.5;
        }
        return fixedSalary;
    }

    @Override
    public int compareTo(Object o) {
        Employee topManager = (Employee) o;
        return Double.compare(getMonthSalary(), topManager.getMonthSalary());
    }

    @Override
    public String toString() {
        return getMonthSalary() + " руб.";
    }
}
