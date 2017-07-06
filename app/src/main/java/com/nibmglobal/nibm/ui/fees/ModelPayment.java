package com.nibmglobal.nibm.ui.fees;

import java.io.Serializable;

public class ModelPayment implements Serializable {
    private String course_id;
    private String dd_amount;
    private String late_fee;
    private String late_fee_tax;
    private String month;
    private String semester_id;
    private String stud_enrol_id;
    private String subject_fee;
    private String subject_fee_including_tax;
    private String subjectfeetaxamount;
    private String tax_amount;
    private String total_including_tax;
    private String total_subjects;
    private String year;

    public String getLate_fee() {
        return this.late_fee;
    }

    public void setLate_fee(String late_fee) {
        this.late_fee = late_fee;
    }

    public String getLate_fee_tax() {
        return this.late_fee_tax;
    }

    public void setLate_fee_tax(String late_fee_tax) {
        this.late_fee_tax = late_fee_tax;
    }

    public String getTax_amount() {
        return this.tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }

    public String getTotal_including_tax() {
        return this.total_including_tax;
    }

    public void setTotal_including_tax(String total_including_tax) {
        this.total_including_tax = total_including_tax;
    }

    public String getSubjectfeetaxamount() {
        return this.subjectfeetaxamount;
    }

    public void setSubjectfeetaxamount(String subjectfeetaxamount) {
        this.subjectfeetaxamount = subjectfeetaxamount;
    }

    public String getSubject_fee_including_tax() {
        return this.subject_fee_including_tax;
    }

    public void setSubject_fee_including_tax(String subject_fee_including_tax) {
        this.subject_fee_including_tax = subject_fee_including_tax;
    }

    public String getSubject_fee() {
        return this.subject_fee;
    }

    public void setSubject_fee(String subject_fee) {
        this.subject_fee = subject_fee;
    }

    public String getDd_amount() {
        return this.dd_amount;
    }

    public void setDd_amount(String dd_amount) {
        this.dd_amount = dd_amount;
    }

    public String getStud_enrol_id() {
        return this.stud_enrol_id;
    }

    public void setStud_enrol_id(String stud_enrol_id) {
        this.stud_enrol_id = stud_enrol_id;
    }

    public String getCourse_id() {
        return this.course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTotal_subjects() {
        return this.total_subjects;
    }

    public void setTotal_subjects(String total_subjects) {
        this.total_subjects = total_subjects;
    }

    public String getSemester_id() {
        return this.semester_id;
    }

    public void setSemester_id(String semester_id) {
        this.semester_id = semester_id;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
