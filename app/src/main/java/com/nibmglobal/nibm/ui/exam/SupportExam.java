package com.nibmglobal.nibm.ui.exam;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/2/15.
 */
public class SupportExam implements Parcelable, Serializable{

    private String examYear;
    private String examMonth;
    private String mode;
    private String eStatus;
    private String examDate;
    private String examToDate;
    private String timeFrom;
    private String timeTo;
    private String courseName;
    private String subjectName;
    private String semesterName;

    public SupportExam() {
    }

    protected SupportExam(Parcel in) {
        examYear = in.readString();
        examMonth = in.readString();
        mode = in.readString();
        eStatus = in.readString();
        examDate = in.readString();
        examToDate = in.readString();
        timeFrom = in.readString();
        timeTo = in.readString();
        courseName = in.readString();
        subjectName = in.readString();
        semesterName = in.readString();
    }

    public static final Creator<SupportExam> CREATOR = new Creator<SupportExam>() {
        @Override
        public SupportExam createFromParcel(Parcel in) {
            return new SupportExam(in);
        }

        @Override
        public SupportExam[] newArray(int size) {
            return new SupportExam[size];
        }
    };

    public String getExamYear() {
        return examYear;
    }

    public void setExamYear(String examYear) {
        this.examYear = examYear;
    }

    public String getExamMonth() {
        return examMonth;
    }

    public void setExamMonth(String examMonth) {
        this.examMonth = examMonth;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String geteStatus() {
        return eStatus;
    }

    public void seteStatus(String eStatus) {
        this.eStatus = eStatus;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamToDate() {
        return examToDate;
    }

    public void setExamToDate(String examToDate) {
        this.examToDate = examToDate;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(examYear);
        dest.writeString(examMonth);
        dest.writeString(mode);
        dest.writeString(eStatus);
        dest.writeString(examDate);
        dest.writeString(examToDate);
        dest.writeString(timeFrom);
        dest.writeString(timeTo);
        dest.writeString(courseName);
        dest.writeString(subjectName);
        dest.writeString(semesterName);
    }
}
