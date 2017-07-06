package com.nibmglobal.nibm.ui.student_activities.dispatched;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mindlabs on 12/2/15.
 */
public class SupportDispatchedMaterials implements Serializable, Parcelable{

    private String id;
    private String dispatchedDate;
    private String dispatchMedium;
    private String issueStatus;
    private String comments;
    private ArrayList<SubSupportDispatchedMaterial> semesters;

    public SupportDispatchedMaterials() {
    }


    protected SupportDispatchedMaterials(Parcel in) {
        id = in.readString();
        dispatchedDate = in.readString();
        dispatchMedium = in.readString();
        issueStatus = in.readString();
        comments = in.readString();
        semesters = in.createTypedArrayList(SubSupportDispatchedMaterial.CREATOR);
    }

    public static final Creator<SupportDispatchedMaterials> CREATOR = new Creator<SupportDispatchedMaterials>() {
        @Override
        public SupportDispatchedMaterials createFromParcel(Parcel in) {
            return new SupportDispatchedMaterials(in);
        }

        @Override
        public SupportDispatchedMaterials[] newArray(int size) {
            return new SupportDispatchedMaterials[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDispatchedDate() {
        return dispatchedDate;
    }

    public void setDispatchedDate(String dispatchedDate) {
        this.dispatchedDate = dispatchedDate;
    }

    public String getDispatchMedium() {
        return dispatchMedium;
    }

    public void setDispatchMedium(String dispatchMedium) {
        this.dispatchMedium = dispatchMedium;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ArrayList<SubSupportDispatchedMaterial> getSemesters() {
        return semesters;
    }

    public void setSemesters(ArrayList<SubSupportDispatchedMaterial> semesters) {
        this.semesters = semesters;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(dispatchedDate);
        dest.writeString(dispatchMedium);
        dest.writeString(issueStatus);
        dest.writeString(comments);
        dest.writeTypedList(semesters);
    }
}
