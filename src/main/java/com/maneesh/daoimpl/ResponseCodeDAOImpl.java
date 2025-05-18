package com.maneesh.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.maneesh.Dao.ResponseCodeDAO;
import com.maneesh.model.ResponseCode;
import com.maneesh.util.DBUtil;

public class ResponseCodeDAOImpl implements ResponseCodeDAO {
    
    @Override
    public List<ResponseCode> getAllResponseCodes() throws SQLException {
        String sql = "SELECT code, image_url FROM all_response_codes";
        List<ResponseCode> codes = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ResponseCode code = new ResponseCode();
                code.setResponseCode(rs.getString("code"));
                code.setImageUrl(rs.getString("image_url"));
                codes.add(code);
            }
        }
        return codes;
    }
    
    @Override
    public ResponseCode getResponseCodeByCode(String code) throws SQLException {
        String sql = "SELECT code, image_url FROM all_response_codes WHERE code = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ResponseCode resp = new ResponseCode();
                resp.setResponseCode(rs.getString("code"));
                resp.setImageUrl(rs.getString("image_url"));
                return resp;
            }
        }
        return null;
    }
    
    @Override
    public List<ResponseCode> getResponseCodesByPattern(String pattern) throws SQLException {
        List<ResponseCode> codes = new ArrayList<>();
        String sql;
        boolean exact = false;
        pattern = pattern.trim();
 
        if (pattern.matches("\\d{3}")) {
            sql = "SELECT code, image_url FROM all_response_codes WHERE code = ?";
            exact = true;
        } else {
            sql = "SELECT code, image_url FROM all_response_codes WHERE code LIKE ?";
        }
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (exact) {
                pstmt.setString(1, pattern);
            } else {
                String likePattern = pattern.replace("x", "_");
                if (!likePattern.contains("_")) {
                    likePattern = likePattern + "%";
                }
                pstmt.setString(1, likePattern);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ResponseCode code = new ResponseCode();
                code.setResponseCode(rs.getString("code"));
                code.setImageUrl(rs.getString("image_url"));
                codes.add(code);
            }
        }
        return codes;
    }
    @Override
    public List<ResponseCode> getResponseCodesByListId(int listId) throws SQLException {
        String sql = "SELECT rc.response_code, arc.image_url FROM response_codes rc JOIN all_response_codes arc ON rc.response_code = arc.code WHERE rc.list_id = ?";
        List<ResponseCode> codes = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, listId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ResponseCode code = new ResponseCode();
                code.setResponseCode(rs.getString("response_code"));
                code.setImageUrl(rs.getString("image_url"));
                codes.add(code);
            }
        }
        return codes;
    }
    
    @Override
    public boolean addResponseCode(ResponseCode code) throws SQLException {
        String sql = "INSERT INTO response_codes (list_id, response_code, image_url) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, code.getListId());
            pstmt.setString(2, code.getResponseCode());
            pstmt.setString(3, code.getImageUrl());
            return pstmt.executeUpdate() > 0;
        }
    }
    
//    @Override
//    public boolean updateResponseCode(ResponseCode code) throws SQLException {
//        String sql = "UPDATE response_codes SET image_url = ? WHERE code_id = ?";
//        try (Connection conn = DBUtil.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, code.getImageUrl());
//            pstmt.setInt(2, code.getCodeId());
//            return pstmt.executeUpdate() > 0;
//        }
//    }
    
    @Override
    public boolean deleteResponseCode(String code) throws SQLException {
        String sql = "DELETE FROM response_codes WHERE response_code = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            return pstmt.executeUpdate() > 0;
        }
    }
} 