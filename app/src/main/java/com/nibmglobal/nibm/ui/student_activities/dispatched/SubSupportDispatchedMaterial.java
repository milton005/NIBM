package com.nibmglobal.nibm.ui.student_activities.dispatched;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/2/15.
 */
public class SubSupportDispatchedMaterial implements Serializable, Parcelable{

    private String semName;
    private String name;

    public SubSupportDispatchedMaterial() {
    }


    protected SubSupportDispatchedMaterial(Parcel in) {
        semName = in.readString();
        name = in.readString();
    }

    public static final Creator<SubSupportDispatchedMaterial> CREATOR = new Creator<SubSupportDispatchedMaterial>() {
        @Override
        public SubSupportDispatchedMaterial createFromParcel(Parcel in) {
            return new SubSupportDispatchedMaterial(in);
        }

        @Override
        public SubSupportDispatchedMaterial[] newArray(int size) {
            return new SubSupportDispatchedMaterial[size];
        }
    };

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
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
        dest.writeString(semName);
        dest.writeString(name);
    }
}
