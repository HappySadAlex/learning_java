package org.practice2;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = ConnectionMySQL.createSessionFactory();

        Session session = sessionFactory.openSession();

        List<LinkedPurchaseList.LinkedPurchaseListKey> keyList = new ArrayList<>();
        //1) Получить пары "название курса" и "имя студента" из PurchaseList
        //2) Найти в таблицах Courses и Students все ID и записать в bothID
        //3) Создать ключи LinkedPurchaseListKey из двух полученных ранее ID и записать их в таблицу LinkedPurchaseList

        //а потом я придумал это...
        //SELECT courses.id, students.id FROM purchaselist
        // JOIN courses ON purchaselist.course_name = courses.name
        // JOIN students ON purchaselist.student_name = students.name;
        String hql = "Select s.id, c.id From purchaseList p JOIN Course c ON p.name = c.name " +
                "JOIN Student s ON p.studentName = s.name";
        TypedQuery<Object[]> queryHQL = session.createQuery(hql, Object[].class);
        List<Object[]> purchaseLists = queryHQL.getResultList();

        //Creating and adding keys for LinkedPurchaseList into keyList
        for (Object[] o : purchaseLists) {
            final Integer studentId = (Integer) o[0];
            final Integer courseId = (Integer) o[1];
            LinkedPurchaseList.LinkedPurchaseListKey ke = new LinkedPurchaseList.LinkedPurchaseListKey(studentId, courseId);
            keyList.add(ke);
            //System.out.println(studentId + "\t" + courseId);
        }
        //System.out.println(keyList.size()); //271

        session.getTransaction().begin();
        //Adding keys(pairs of id) into table LinkedPurchaseList
        for(LinkedPurchaseList.LinkedPurchaseListKey key : keyList){
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setId(key);
            session.persist(linkedPurchaseList);
        }
        session.getTransaction().commit();
        session.close();

        //Query for check that all is right: "SELECT c.id, c.name, s.id, s.name, p.course_name, p.student_name
        // FROM purchaselist p JOIN students s ON p.student_name = s.name JOIN courses c ON p.course_name = c.name";

    }
}