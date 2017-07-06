package com.nibmglobal.nibm.ui.download;


import java.util.ArrayList;

/**
 * Created by mindlabs on 12/10/15.
 */
public class InsertCourse {
    ArrayList<SupportCourse> allCorse = new ArrayList<>();
    SupportCourse course;

    public InsertCourse() {

        course = new SupportCourse();
        course.setName("Course");
        course.setId("-1");
        allCorse.add(course);

        course = new SupportCourse();
        course.setName("ne year Online MBA");
        course.setId("1");
        allCorse.add(course);

        course = new SupportCourse();
        course.setName("One year Online Exe MBA");
        course.setId("2");
        allCorse.add(course);

        course = new SupportCourse();
        course.setName("Two year Online MBA");
        course.setId("3");
        allCorse.add(course);

        course = new SupportCourse();
        course.setName("One year Online PGDBA");
        course.setId("4");
        allCorse.add(course);

        course = new SupportCourse();
        course.setName("Fast Track MBA");
        course.setId("14");
        allCorse.add(course);


        course = new SupportCourse();
        course.setName("Six months Online DBA");
        course.setId("22");
        allCorse.add(course);

    }

    public ArrayList<SupportCourse> getCourses() {
        return allCorse;
    }
}
