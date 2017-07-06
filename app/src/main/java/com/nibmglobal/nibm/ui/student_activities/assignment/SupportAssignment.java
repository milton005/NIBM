package com.nibmglobal.nibm.ui.student_activities.assignment;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/2/15.
 */
public class SupportAssignment implements Parcelable, Serializable{

    private String assignmentTitle;
    private String assignmentType;
    private String batchYear;
    private String courseName;
    private String semsterName;
    private String assignmentDate;
    private String completeion_date;
    private String assignmentFile;
    private String batchMonth;

    public SupportAssignment() {
    }

    protected SupportAssignment(Parcel in) {
        assignmentTitle = in.readString();
        assignmentType = in.readString();
        batchYear = in.readString();
        courseName = in.readString();
        semsterName = in.readString();
        assignmentDate = in.readString();
        completeion_date = in.readString();
        assignmentFile = in.readString();
        batchMonth = in.readString();
    }

    public static final Creator<SupportAssignment> CREATOR = new Creator<SupportAssignment>() {
        @Override
        public SupportAssignment createFromParcel(Parcel in) {
            return new SupportAssignment(in);
        }

        @Override
        public SupportAssignment[] newArray(int size) {
            return new SupportAssignment[size];
        }
    };

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getBatchMonth() {
        return batchMonth;
    }

    public void setBatchMonth(String batchMonth) {
        this.batchMonth = batchMonth;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getBatchYear() {
        return batchYear;
    }

    public void setBatchYear(String batchYear) {
        this.batchYear = batchYear;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemsterName() {
        return semsterName;
    }

    public void setSemsterName(String semsterName) {
        this.semsterName = semsterName;
    }

    public String getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(String assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public String getCompleteion_date() {
        return completeion_date;
    }

    public void setCompleteion_date(String completeion_date) {
        this.completeion_date = completeion_date;
    }

    public String getAssignmentFile() {
        return assignmentFile;
    }

    public void setAssignmentFile(String assignmentFile) {
        this.assignmentFile = assignmentFile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(assignmentTitle);
        dest.writeString(assignmentType);
        dest.writeString(batchYear);
        dest.writeString(courseName);
        dest.writeString(semsterName);
        dest.writeString(assignmentDate);
        dest.writeString(completeion_date);
        dest.writeString(assignmentFile);
        dest.writeString(batchMonth);
    }
}
