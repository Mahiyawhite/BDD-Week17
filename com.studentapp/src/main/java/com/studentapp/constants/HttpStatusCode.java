package com.studentapp.constants;



public enum HttpStatusCode {

    OK(200,"ok");

    private final int code;
    private final String description;
    HttpStatusCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public  int getCode(){
        return code;
    }

   public String getDescription(){
        return description;
   }
    public String toString(){
        return code + " " + description;
    }

}
