package com.patika.week2.Services;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Response {

    private String timestamp;
    private String returnStatus;

    public Response() {}

    private String getCurrentDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        return dateFormatter.format(new Date());
    }

    public Response(String returnStatus) {
        this.timestamp = getCurrentDate();
        this.returnStatus = returnStatus;
    }

    public String getReturnStatus(){
        return this.returnStatus;
    }

    public String getTimestamp(){
        return this.timestamp;
    }

}
