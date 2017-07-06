package com.nibmglobal.nibm.ui.questionpaper;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/10/15.
 */
public class SupportQuestionPaper implements Serializable, Parcelable {

    private String id;
    private String year;
    private String month;
    private String mode;
    private String examDate;
    private String timeFrom;
    private String timeTo;
    private String endtime;
    private String fromDate;
    private String toDate;
    private String courseName;
    private String subjectName;
    private String scheduleName;
    private String semsesteName;
    private String examPaperfile;

    public SupportQuestionPaper() {
    }

    protected SupportQuestionPaper(Parcel in) {
        id = in.readString();
        year = in.readString();
        month = in.readString();
        mode = in.readString();
        examDate = in.readString();
        timeFrom = in.readString();
        timeTo = in.readString();
        endtime = in.readString();
        fromDate = in.readString();
        toDate = in.readString();
        courseName = in.readString();
        subjectName = in.readString();
        scheduleName = in.readString();
        semsesteName = in.readString();
        examPaperfile = in.readString();
    }

    public static final Creator<SupportQuestionPaper> CREATOR = new Creator<SupportQuestionPaper>() {
        @Override
        public SupportQuestionPaper createFromParcel(Parcel in) {
            return new SupportQuestionPaper(in);
        }

        @Override
        public SupportQuestionPaper[] newArray(int size) {
            return new SupportQuestionPaper[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
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

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
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

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getSemsesteName() {
        return semsesteName;
    }

    public void setSemsesteName(String semsesteName) {
        this.semsesteName = semsesteName;
    }

    public String getExamPaperfile() {
        return examPaperfile;
    }

    public void setExamPaperfile(String examPaperfile) {
        this.examPaperfile = examPaperfile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(year);
        dest.writeString(month);
        dest.writeString(mode);
        dest.writeString(examDate);
        dest.writeString(timeFrom);
        dest.writeString(timeTo);
        dest.writeString(endtime);
        dest.writeString(fromDate);
        dest.writeString(toDate);
        dest.writeString(courseName);
        dest.writeString(subjectName);
        dest.writeString(scheduleName);
        dest.writeString(semsesteName);
        dest.writeString(examPaperfile);
    }
}
