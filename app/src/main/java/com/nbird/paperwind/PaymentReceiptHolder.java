package com.nbird.paperwind;

public class PaymentReceiptHolder {
    String payment_id;
    String amount;
    String mail;
    String number;
    String time;

    public PaymentReceiptHolder() {
    }

    public PaymentReceiptHolder(String payment_id, String amount, String mail, String number,String time) {
        this.payment_id = payment_id;
        this.amount = amount;
        this.mail = mail;
        this.number = number;
        this.time=time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
