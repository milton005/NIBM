package com.nibmglobal.nibm.ui.fees;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/2/15.
 */
public class SupportFees implements Parcelable, Serializable {

    private String feesName;
    private String feesAmount;
    private String paidFees;
    private String payStatus;
    private String paymentType;
    private String reciptNo;
    private String paymentMode;
    private String ddNo;
    private String ddDate;
    private String checqueNo;
    private String checqueDate;
    private String bankName;
    private String cardType;
    private String cardNumber;
    private String taransationId;
    private String transationDate;

    public SupportFees() {
    }

    protected SupportFees(Parcel in) {
        feesName = in.readString();
        feesAmount = in.readString();
        paidFees = in.readString();
        payStatus = in.readString();
        paymentType = in.readString();
        reciptNo = in.readString();
        paymentMode = in.readString();
        ddNo = in.readString();
        ddDate = in.readString();
        checqueNo = in.readString();
        checqueDate = in.readString();
        bankName = in.readString();
        cardType = in.readString();
        cardNumber = in.readString();
        taransationId = in.readString();
        transationDate = in.readString();
    }

    public static final Creator<SupportFees> CREATOR = new Creator<SupportFees>() {
        @Override
        public SupportFees createFromParcel(Parcel in) {
            return new SupportFees(in);
        }

        @Override
        public SupportFees[] newArray(int size) {
            return new SupportFees[size];
        }
    };

    public String getFeesName() {
        return feesName;
    }

    public void setFeesName(String feesName) {
        this.feesName = feesName;
    }

    public String getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(String feesAmount) {
        this.feesAmount = feesAmount;
    }

    public String getPaidFees() {
        return paidFees;
    }

    public void setPaidFees(String paidFees) {
        this.paidFees = paidFees;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getReciptNo() {
        return reciptNo;
    }

    public void setReciptNo(String reciptNo) {
        this.reciptNo = reciptNo;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDdNo() {
        return ddNo;
    }

    public void setDdNo(String ddNo) {
        this.ddNo = ddNo;
    }

    public String getDdDate() {
        return ddDate;
    }

    public void setDdDate(String ddDate) {
        this.ddDate = ddDate;
    }

    public String getChecqueNo() {
        return checqueNo;
    }

    public void setChecqueNo(String checqueNo) {
        this.checqueNo = checqueNo;
    }

    public String getChecqueDate() {
        return checqueDate;
    }

    public void setChecqueDate(String checqueDate) {
        this.checqueDate = checqueDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getTaransationId() {
        return taransationId;
    }

    public void setTaransationId(String taransationId) {
        this.taransationId = taransationId;
    }

    public String getTransationDate() {
        return transationDate;
    }

    public void setTransationDate(String transationDate) {
        this.transationDate = transationDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(feesName);
        dest.writeString(feesAmount);
        dest.writeString(paidFees);
        dest.writeString(payStatus);
        dest.writeString(paymentType);
        dest.writeString(reciptNo);
        dest.writeString(paymentMode);
        dest.writeString(ddNo);
        dest.writeString(ddDate);
        dest.writeString(checqueNo);
        dest.writeString(checqueDate);
        dest.writeString(bankName);
        dest.writeString(cardType);
        dest.writeString(cardNumber);
        dest.writeString(taransationId);
        dest.writeString(transationDate);
    }
}
