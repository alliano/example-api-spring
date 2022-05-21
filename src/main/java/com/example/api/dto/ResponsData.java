package com.example.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponsData<T> {

    private boolean status;
    private List<String> messagesList = new ArrayList<>();
    private T paylooad;

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public List<String> getMessagesList() {
        return messagesList;
    }
    public void setMessagesList(List<String> messagesList) {
        this.messagesList = messagesList;
    }
    public T getPaylooad() {
        return paylooad;
    }
    public void setPaylooad(T paylooad) {
        this.paylooad = paylooad;
    }
    
    
}
