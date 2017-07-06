package com.nibmglobal.nibm.ui.student_activities.journal;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/2/15.
 */
public class SupportJournal implements Parcelable, Serializable{

    private String journalType;
    private String title;
    private String volume;
    private String year;
    private String month;
    private String journalFile;
    private String fileUrl;

    public SupportJournal() {
    }

    protected SupportJournal(Parcel in) {
        journalType = in.readString();
        title = in.readString();
        volume = in.readString();
        year = in.readString();
        month = in.readString();
        journalFile = in.readString();
        fileUrl = in.readString();
    }

    public static final Creator<SupportJournal> CREATOR = new Creator<SupportJournal>() {
        @Override
        public SupportJournal createFromParcel(Parcel in) {
            return new SupportJournal(in);
        }

        @Override
        public SupportJournal[] newArray(int size) {
            return new SupportJournal[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
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

    public String getJournalFile() {
        return journalFile;
    }

    public void setJournalFile(String journalFile) {
        this.journalFile = journalFile;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getJournalType() {
        return journalType;
    }

    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(journalType);
        dest.writeString(title);
        dest.writeString(volume);
        dest.writeString(year);
        dest.writeString(month);
        dest.writeString(journalFile);
        dest.writeString(fileUrl);
    }
}
