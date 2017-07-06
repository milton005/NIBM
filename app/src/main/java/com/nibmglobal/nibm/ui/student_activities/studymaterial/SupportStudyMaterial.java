package com.nibmglobal.nibm.ui.student_activities.studymaterial;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/2/15.
 */
public class SupportStudyMaterial implements Parcelable, Serializable {

    private String id;
    private String semester;
    private String book;
    private String name;

    public SupportStudyMaterial() {
    }

    protected SupportStudyMaterial(Parcel in) {
        id = in.readString();
        semester = in.readString();
        book = in.readString();
        name = in.readString();
    }

    public static final Creator<SupportStudyMaterial> CREATOR = new Creator<SupportStudyMaterial>() {
        @Override
        public SupportStudyMaterial createFromParcel(Parcel in) {
            return new SupportStudyMaterial(in);
        }

        @Override
        public SupportStudyMaterial[] newArray(int size) {
            return new SupportStudyMaterial[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(semester);
        dest.writeString(book);
        dest.writeString(name);
    }
}
