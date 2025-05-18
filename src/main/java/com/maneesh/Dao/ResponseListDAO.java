package com.maneesh.Dao;

import java.sql.SQLException;
import java.util.List;

import com.maneesh.model.ResponseList;

public interface ResponseListDAO {
    // Create a new response list
    boolean createList(ResponseList list) throws SQLException;
    
    // Get list by ID
    ResponseList getListById(int listId) throws SQLException;
    
    // Get all lists for a user
    List<ResponseList> getListsByUserId(int userId) throws SQLException;
    
    // Update list information
    boolean updateList(ResponseList list) throws SQLException;
    
    // Delete list
    boolean deleteList(int listId) throws SQLException;
    
    // Add response code to list
    boolean addResponseCodeToList(int listId, String responseCode) throws SQLException;
    
    // Remove response code from list
    boolean removeResponseCodeFromList(int listId, String responseCode) throws SQLException;
    
    // Get all response codes in a list
    List<String> getResponseCodesInList(int listId) throws SQLException;
} 