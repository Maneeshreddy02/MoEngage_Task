package com.maneesh.Dao;

import java.sql.SQLException;
import java.util.List;

import com.maneesh.model.ResponseCode;

public interface ResponseCodeDAO {
    // Get all response codes
    List<ResponseCode> getAllResponseCodes() throws SQLException;
    
    // Get response code by code
    ResponseCode getResponseCodeByCode(String code) throws SQLException;
    
    // Get response codes by pattern (e.g., "2xx", "20x", etc.)oo
    List<ResponseCode> getResponseCodesByPattern(String pattern) throws SQLException;
    
    
    // Get response codes by list ID
    List<ResponseCode> getResponseCodesByListId(int listId) throws SQLException;
    
    // Add new response code
    boolean addResponseCode(ResponseCode code) throws SQLException;
    
    // Update response code
//    boolean updateResponseCode(ResponseCode code) throws SQLException;
    
    // Delete response code
    boolean deleteResponseCode(String code) throws SQLException;
}