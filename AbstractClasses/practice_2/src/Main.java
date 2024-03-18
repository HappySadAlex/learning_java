import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //���������� 180 ����������
        Company cola = new Company(12_000_000);
        for(int i = 0; i < 180; i++){
            cola.hire(new Operator());
        }
        //���������� 80 ����������
        for (int i = 0; i < 80; i++){
            cola.hire(new Manager());
        }
        //���������� 10 ���-����������
        for(int i = 0; i < 10; i++){
            cola.hire(new TopManager(cola));
        }

        System.out.println("Now count of employees is: " + cola.getCountOfEmployees());
        //����� 10 ����������� � ��� �/� � 30 � �����������
        for(Employee emp : cola.getTopSalaryStaff(10)){
            System.out.println(emp);
        }
        System.out.println("---------------");
        for(Employee emp : cola.getLowestSalaryStaff(30)){
            System.out.println(emp);
        }
        //���������� 50% ����������
        System.out.println("----- Fire 50% -----");
        int halfStaff = cola.getCountOfEmployees() / 2; // ���-�� ����������� �� ����������
        for (int i = 0; i != halfStaff; i++){
            cola.fire(cola.staff.get(i));
        }
        //������� ����������� ��������
        System.out.println("Now count of employees is: " + cola.getCountOfEmployees());
        //����� 10 ����������� � ��� �/� � 30 � �����������
        for(Employee emp : cola.getTopSalaryStaff(10)){
            System.out.println(emp);
        }
        System.out.println("---------------");
        for(Employee emp : cola.getLowestSalaryStaff(30)){
            System.out.println(emp);
        }
    }
}
