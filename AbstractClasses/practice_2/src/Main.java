import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //добавление 180 операторов
        Company cola = new Company(12_000_000);
        for(int i = 0; i < 180; i++){
            cola.hire(new Operator());
        }
        //добавление 80 менеджеров
        for (int i = 0; i < 80; i++){
            cola.hire(new Manager());
        }
        //добавление 10 топ-менеджеров
        for(int i = 0; i < 10; i++){
            cola.hire(new TopManager(cola));
        }

        System.out.println("Now count of employees is: " + cola.getCountOfEmployees());
        //вывод 10 сотрудников с топ з/п и 30 с наименьшими
        for(Employee emp : cola.getTopSalaryStaff(10)){
            System.out.println(emp);
        }
        System.out.println("---------------");
        for(Employee emp : cola.getLowestSalaryStaff(30)){
            System.out.println(emp);
        }
        //увольнение 50% работников
        System.out.println("----- Fire 50% -----");
        int halfStaff = cola.getCountOfEmployees() / 2; // кол-во сотрудников на увольнение
        for (int i = 0; i != halfStaff; i++){
            cola.fire(cola.staff.get(i));
        }
        //сколько сотрудников осталось
        System.out.println("Now count of employees is: " + cola.getCountOfEmployees());
        //вывод 10 сотрудников с топ з/п и 30 с наименьшими
        for(Employee emp : cola.getTopSalaryStaff(10)){
            System.out.println(emp);
        }
        System.out.println("---------------");
        for(Employee emp : cola.getLowestSalaryStaff(30)){
            System.out.println(emp);
        }
    }
}
