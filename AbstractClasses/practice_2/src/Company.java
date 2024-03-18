import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Company {

    private double income = 0;
    private int countOfEmployees = 0;
    public List<Employee> staff = new ArrayList<>();

    public List<Employee> getTopSalaryStaff(int count){
        ArrayList<Employee> topSalaryStaff = new ArrayList<>();
        Collections.sort(staff, Employee::compareTo);
        System.out.println("Top salary staff: ");
        for(int i = staff.size() - 1; i > 0 && count != 0; i--, count--){
            topSalaryStaff.add(staff.get(i));
        }
        //Collections.sort(topSalaryStaff, Employee::compareTo);
        return topSalaryStaff;
    }

    public List<Employee> getLowestSalaryStaff(int count){
        ArrayList<Employee> lowestSalaryStaff = new ArrayList<>();
        Collections.sort(staff, Employee::compareTo);
        System.out.println("Lowest salary staff: ");
        for(int i = 0; i < count; i++){
            lowestSalaryStaff.add(staff.get(i));
        }
        Collections.sort(lowestSalaryStaff, Employee::compareTo);
        return lowestSalaryStaff;
    }

    public Company(){
        this.income = getIncome();
    }

    public Company(double income) {
        this.income = income;
        staff = new ArrayList<Employee>();
    }

    public void hire(Employee employee){
        staff.add(employee);
        countOfEmployees++;
    }

    public void hireAll(Collection<Employee> employees){
        staff.addAll(employees);
        countOfEmployees += employees.size();
    }

    public void fire(Employee employee){
        staff.remove(employee);
        countOfEmployees--;
    }

    public double getIncome(){
        return income;
    }

    public int getCountOfEmployees() {
        return countOfEmployees;
    }

}
