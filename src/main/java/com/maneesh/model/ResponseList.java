package com.maneesh.model;

import java.sql.Timestamp;
import java.util.List;

public class ResponseList {
    private int listId;
    private int userId;
    private String listName;
    private Timestamp createdAt;
    private List<ResponseCode> responseCodes;

    // Default constructor
    public ResponseList() {}

    // Constructor with all fields
    public ResponseList(int listId, int userId, String listName, Timestamp createdAt) {
        this.listId = listId;
        this.userId = userId;
        this.listName = listName;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<ResponseCode> getResponseCodes() {
        return responseCodes;
    }

    public void setResponseCodes(List<ResponseCode> responseCodes) {
        this.responseCodes = responseCodes;
    }
} 