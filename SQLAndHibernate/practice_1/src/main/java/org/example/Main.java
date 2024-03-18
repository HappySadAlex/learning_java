package org.example;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.*;


public class Main {
    public static void main(String[] args){

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/skillbox?useSSL=false", "root", "AlexAlex");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT course_name, COUNT(course_name)/(MAX(MONTH(subscription_date)) -" +
                    " MIN(MONTH(subscription_date)) + 1) AS date FROM PurchaseList WHERE purchaselist.subscription_date BETWEEN '2018-01-01 00:00:00' AND '2018-12-31 23:59:59'" +
                    " GROUP BY course_name");
            while (resultSet.next()) {
                String result = resultSet.getString("course_name") + " - " + resultSet.getString("date");
                System.out.println(result);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }



        /*SessionFactory sessionFactory = ConnectionMySQL.createSessionFactory();

        Session session = sessionFactory.openSession();
        TypedQuery<Course> queryCourse = session.createQuery("FROM Course", Course.class);
        // getting courses from DB
        List<Course> coursesList = queryCourse.getResultList();
        // adding courses names to list
        List<String> coursesNames = new ArrayList<>();
        for(Course c : coursesList){
            coursesNames.add(c.getName());
        }

        //Building query to find purchases of courses in 2018 year
        *//*CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<purchaseList> query = builder.createQuery(purchaseList.class);
        Root<purchaseList> root = query.from(purchaseList.class);
        Timestamp fromDate = Timestamp.valueOf("2018-01-01 00:00:00.000");
        Timestamp toDate = Timestamp.valueOf("2018-12-31 23:59:59.000");
        query.where(builder.between(root.<Timestamp>get("date"), fromDate, toDate));*//*

        Timestamp fromDate = Timestamp.valueOf("2018-01-01 00:00:00.000");
        Timestamp toDate = Timestamp.valueOf("2018-12-31 23:59:59.000");

        //I was trying to make sorting by subscription_date with all these methods, but any of it didn't work, at 4th month 'order by' goes crazy and start from 1st month...
        //query.orderBy(builder.asc(root.get("date")));
        //query.orderBy(builder.asc(root.<Timestamp>get("date")))

        String hql = "Select name, MONTH(date) " + "From " + purchaseList.class.getSimpleName() + " WHERE date BETWEEN :paramFrom AND :paramTo ORDER BY date";
        TypedQuery<Object[]> queryHQL = session.createQuery(hql, Object[].class);
        queryHQL.setParameter("paramFrom", fromDate);
        queryHQL.setParameter("paramTo", toDate);
        //getting from DB pairs of course and month when it was bought (SORTED!)
        List<Object[]> purchaseLists = queryHQL.getResultList();
        session.close();

        LinkedHashMap<String, List<Integer>> cour = new LinkedHashMap<>();
        for(String course : coursesNames) {
            List<Integer> cAm = new ArrayList<>(); //course and month
            for (Object[] o : purchaseLists) {
                final String name = (String) o[0];
                final Integer mon = (Integer) o[1];
                if(name.equals(course)){
                    cAm.add(mon);
                }
            }
            cour.put(course, cAm);
            //System.out.println("Course: " + course + "\t months: " + cAm);
        }

        for(String course : coursesNames){
            float result = 0;
            for (Map.Entry<String,List<Integer>> entry : cour.entrySet()) {
                List<Integer> temp = entry.getValue();
                if(entry.getKey().equals(course) && !temp.isEmpty()){
                    int times = temp.size();
                    int monthsOfSelling = temp.get(times-1) - temp.get(0) + 1;
                    result = (float) times / monthsOfSelling;
                }
            }
            System.out.println("Course: " + course + "\t Average selling: " + result);
        }
        sessionFactory.close();*/
    }
}