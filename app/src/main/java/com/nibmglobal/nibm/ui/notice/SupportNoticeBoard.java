package com.nibmglobal.nibm.ui.notice;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/2/15.
 */
public class SupportNoticeBoard implements Parcelable, Serializable{

    private String noticeMessage;
    private String sendDate;

    public SupportNoticeBoard() {
    }

    protected SupportNoticeBoard(Parcel in) {
        noticeMessage = in.readString();
        sendDate = in.readString();
    }

    public static final Creator<SupportNoticeBoard> CREATOR = new Creator<SupportNoticeBoard>() {
        @Override
        public SupportNoticeBoard createFromParcel(Parcel in) {
            return new SupportNoticeBoard(in);
        }

        @Override
        public SupportNoticeBoard[] newArray(int size) {
            return new SupportNoticeBoard[size];
        }
    };

    public String getNoticeMessage() {
        return noticeMessage;
    }

    public void setNoticeMessage(String noticeMessage) {
        this.noticeMessage = noticeMessage;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noticeMessage);
        dest.writeString(sendDate);
    }
}
