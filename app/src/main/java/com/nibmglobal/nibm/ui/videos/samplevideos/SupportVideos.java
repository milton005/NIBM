package com.nibmglobal.nibm.ui.videos.samplevideos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/9/15.
 */
public class SupportVideos implements Serializable,Parcelable {

    private String videoName;
    private String videoThumb;
    private String videoThumbUrl;
    private String VideoUrl;

    private String subjectNAme;
    private String courseName;

    public SupportVideos() {
    }

    protected SupportVideos(Parcel in) {
        videoName = in.readString();
        videoThumb = in.readString();
        videoThumbUrl = in.readString();
        VideoUrl = in.readString();
        subjectNAme = in.readString();
        courseName = in.readString();
    }

    public static final Creator<SupportVideos> CREATOR = new Creator<SupportVideos>() {
        @Override
        public SupportVideos createFromParcel(Parcel in) {
            return new SupportVideos(in);
        }

        @Override
        public SupportVideos[] newArray(int size) {
            return new SupportVideos[size];
        }
    };

    public String getSubjectNAme() {
        return subjectNAme;
    }

    public void setSubjectNAme(String subjectNAme) {
        this.subjectNAme = subjectNAme;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoThumb() {
        return videoThumb;
    }

    public void setVideoThumb(String videoThumb) {
        this.videoThumb = videoThumb;
    }

    public String getVideoThumbUrl() {
        return videoThumbUrl;
    }

    public void setVideoThumbUrl(String videoThumbUrl) {
        this.videoThumbUrl = videoThumbUrl;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoName);
        dest.writeString(videoThumb);
        dest.writeString(videoThumbUrl);
        dest.writeString(VideoUrl);
        dest.writeString(subjectNAme);
        dest.writeString(courseName);
    }
}
