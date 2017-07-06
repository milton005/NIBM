package com.nibmglobal.nibm.ui.videos.privatevideos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class SupportPrivateVideo implements Serializable, Parcelable {
    private String subjectID;
    private String subjectName;

    public SupportPrivateVideo() {
    }

    protected SupportPrivateVideo(Parcel in) {
        subjectID = in.readString();
        subjectName = in.readString();
    }

    public static final Creator<SupportPrivateVideo> CREATOR = new Creator<SupportPrivateVideo>() {
        @Override
        public SupportPrivateVideo createFromParcel(Parcel in) {
            return new SupportPrivateVideo(in);
        }

        @Override
        public SupportPrivateVideo[] newArray(int size) {
            return new SupportPrivateVideo[size];
        }
    };

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subjectID);
        dest.writeString(subjectName);
    }
}
