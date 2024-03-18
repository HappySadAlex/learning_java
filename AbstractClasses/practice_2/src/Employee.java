public interface Employee {

    double fixedSalary = 10000;
    //double companyIn = new Company().getIncome();

    double getMonthSalary();

    int compareTo(Object o);

}
