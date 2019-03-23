package com.example.harun.bulksms.Model;

import java.util.List;

public class Message {

    String Message;
    List<String> sendedPeople;
    List<String> deliveredPeople;
    List<String> nonDeliveredPeople;

    public Message(String message, List<String> sendedPeople, List<String> deliveredPeople, List<String> nonDeliveredPeople) {
        Message = message;
        this.sendedPeople = sendedPeople;
        this.deliveredPeople = deliveredPeople;
        this.nonDeliveredPeople = nonDeliveredPeople;
    }

    public Message() {
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<String> getSendedPeople() {
        return sendedPeople;
    }

    public void setSendedPeople(List<String> sendedPeople) {
        this.sendedPeople = sendedPeople;
    }

    public List<String> getDeliveredPeople() {
        return deliveredPeople;
    }

    public void setDeliveredPeople(List<String> deliveredPeople) {
        this.deliveredPeople = deliveredPeople;
    }

    public List<String> getNonDeliveredPeople() {
        return nonDeliveredPeople;
    }

    public void setNonDeliveredPeople(List<String> nonDeliveredPeople) {
        this.nonDeliveredPeople = nonDeliveredPeople;
    }
}
