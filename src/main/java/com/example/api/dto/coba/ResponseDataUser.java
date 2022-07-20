package com.example.api.dto.coba;

import java.util.ArrayList;
import java.util.List;

public class ResponseDataUser<T> {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private T payload;
    
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public T getPayload() {
        return payload;
    }
    public void setPayload(T payload) {
        this.payload = payload;
    }
    public List<String> getMessages() {
        return messages;
    }
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    

}
