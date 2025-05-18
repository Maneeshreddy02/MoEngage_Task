package com.maneesh.model;



public class ResponseCode {
    private int codeId;
    private int listId;
    private String responseCode;
    private String imageUrl;

    // Default constructor
    public ResponseCode() {}

    // Constructor with all fields
    public ResponseCode(int codeId, int listId, String responseCode, String imageUrl) {
        this.codeId = codeId;
        this.listId = listId;
        this.responseCode = responseCode;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
} 
