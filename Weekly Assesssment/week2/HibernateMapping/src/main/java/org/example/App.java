package org.example;

import Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.Insert;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();


        System.out.println("1. Insert Details");
        System.out.println("2. Fetch Details");
        System.out.println(" ");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {

            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();

            // Create a Department
            System.out.print("Enter Department Name: ");
            String deptName = sc.nextLine();
            Department dept = new Department();
            dept.setName(deptName);

            // Create two Students and assign to same Department
            System.out.print("Enter Student 1 Name: ");
            String s1Name = sc.nextLine();
            Student s1 = new Student();
            s1.setName(s1Name);
            s1.setDepartment(dept);

            System.out.print("Enter Student 2 Name: ");
            String s2Name = sc.nextLine();
            Student s2 = new Student();
            s2.setName(s2Name);
            s2.setDepartment(dept);

            dept.getStudents().add(s1);
            dept.getStudents().add(s2);

            // Create ID cards for each student
            System.out.print("Enter ID Card Number for " + s1Name + ": ");
            String cardNo1 = sc.nextLine();
            IDCard card1 = new IDCard();
            card1.setCardNumber(cardNo1);
            s1.setIdCard(card1);

            System.out.print("Enter ID Card Number for " + s2Name + ": ");
            String cardNo2 = sc.nextLine();
            IDCard card2 = new IDCard();
            card2.setCardNumber(cardNo2);
            s2.setIdCard(card2);

            // Create two Courses
            System.out.print("Enter Course 1 Name: ");
            String c1Name = sc.nextLine();
            Course c1 = new Course();
            c1.setCourseName(c1Name);

            System.out.print("Enter Course 2 Name: ");
            String c2Name = sc.nextLine();
            Course c2 = new Course();
            c2.setCourseName(c2Name);

            // Enroll students in courses (Many-to-Many)
            s1.getCourses().add(c1);
            s1.getCourses().add(c2);
            s2.getCourses().add(c1);
            s2.getCourses().add(c2);

            // Save everything
            session.save(dept);
            session.save(s1);
            session.save(s2);

            tx.commit();
            session.close();

            System.out.println("   Saved Successfully  ");
            System.out.println("Department  : " + deptName);
            System.out.println("Student 1   : " + s1Name + " | ID Card: " + cardNo1);
            System.out.println("Student 2   : " + s2Name + " | ID Card: " + cardNo2);
            System.out.println("Course 1    : " + c1Name);
            System.out.println("Course 2    : " + c2Name);
            System.out.println("Both students enrolled in both courses.");
            System.out.println("  ");

        } else if (choice == 2) {

            Session session = factory.openSession();

            // Fetch all Departments
            System.out.println("  Departments  ");
            List<Department> depts = session.createQuery("FROM Department", Department.class).list();
            for (Department d : depts) {
                System.out.println("  ID: " + d.getId() + " | Name: " + d.getName());
            }

            // Fetch all Students with Department and ID Card

            List<Student> students = session.createQuery("FROM Student", Student.class).list();
            for (Student st : students) {
                System.out.println("  ID: " + st.getId()
                        + " | Name: " + st.getName()
                        + " | Department: " + st.getDepartment().getName()
                        + " | ID Card: " + (st.getIdCard() != null ? st.getIdCard().getCardNumber() : "N/A"));
            }

            // Fetch all Courses

            List<Course> courses = session.createQuery("FROM Course", Course.class).list();
            for (Course c : courses) {
                System.out.println("  ID: " + c.getId() + " | Course: " + c.getCourseName());
            }

            // Fetch Student-Course enrollments

            for (Student st : students) {
                System.out.print("  " + st.getName() + " enrolled in: ");
                if (st.getCourses().isEmpty()) {
                    System.out.println("No courses");
                } else {
                    for (Course c : st.getCourses()) {
                        System.out.print(c.getCourseName() + "  ");
                    }
                    System.out.println();
                }
            }

            session.close();

        } else {
            System.out.println("Invalid choice!");
        }

        factory.close();
        sc.close();
    }
}
