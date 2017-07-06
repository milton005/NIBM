package com.nibmglobal.nibm.ui.course;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/2/15.
 */
public class SupportCourse implements Serializable {

    private String courseTitle;
    private String courseDetails;

    public String getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
