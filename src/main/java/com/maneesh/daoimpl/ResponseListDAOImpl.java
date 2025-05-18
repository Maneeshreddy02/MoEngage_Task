package com.maneesh.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.maneesh.Dao.ResponseListDAO;
import com.maneesh.model.ResponseList;
import com.maneesh.util.DBUtil;

public class ResponseListDAOImpl implements ResponseListDAO {
    
    @Override
    public boolean createList(ResponseList list) throws SQLException {
        String sql = "INSERT INTO response_lists (user_id, list_name) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, list.getUserId());
            pstmt.setString(2, list.getListName());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        list.setListId(rs.getInt(1));
                        return true;
                    }
                }
            }
            return false;
        }
    }
    
    @Override
    public ResponseList getListById(int listId) throws SQLException {
        String sql = "SELECT * FROM response_lists WHERE list_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, listId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractListFromResultSet(rs);
            }
        }
        return null;
    }
    
    @Override
    public List<ResponseList> getListsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM response_lists WHERE user_id = ?";
        List<ResponseList> lists = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lists.add(extractListFromResultSet(rs));
            }
        }
        return lists;
    }
    
    @Override
    public boolean updateList(ResponseList list) throws SQLException {
        String sql = "UPDATE response_lists SET list_name = ? WHERE list_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, list.getListName());
            pstmt.setInt(2, list.getListId());
            return pstmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteList(int listId) throws SQLException {
        String sql = "DELETE FROM response_lists WHERE list_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, listId);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean addResponseCodeToList(int listId, String responseCode) throws SQLException {
        String sql = "INSERT INTO response_codes (list_id, response_code) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, listId);
            pstmt.setString(2, responseCode);
            return pstmt.executeUpdate() > 0;
        }
    }
    @Override
    public boolean removeResponseCodeFromList(int listId, String responseCode) throws SQLException {
        String sql = "DELETE FROM response_codes WHERE list_id = ? AND response_code = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, listId);
            pstmt.setString(2, responseCode);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public List<String> getResponseCodesInList(int listId) throws SQLException {
        String sql = "SELECT * FROM response_codes WHERE response_code LIKE ? AND list_id IS NULL";
        List<String> codes = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, listId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                codes.add(rs.getString("response_code"));
            }
        }
        return codes;
    }
    
    private ResponseList extractListFromResultSet(ResultSet rs) throws SQLException {
        ResponseList list = new ResponseList();
        list.setListId(rs.getInt("list_id"));
        list.setUserId(rs.getInt("user_id"));
        list.setListName(rs.getString("list_name"));
        list.setCreatedAt(rs.getTimestamp("created_at"));
       
        return list;
    }
} 
