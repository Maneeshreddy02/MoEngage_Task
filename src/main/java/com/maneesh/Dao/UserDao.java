package com.maneesh.Dao;

import java.sql.SQLException;

import com.maneesh.model.User;


	public interface UserDao {
	    // Create a new user
	    boolean createUser(User user) throws SQLException;
	    
	    // Get user by username
	    User getUserByUsername(String username) throws SQLException;
	    
	    // Get user by email
	    User getUserByEmail(String email) throws SQLException;
	    
	    // Verify user credentials
	    User verifyUser(String username, String password) throws SQLException;
	    
	    // Update user information
	    boolean updateUser(User user) throws SQLException;
	    
	    // Delete user
	    boolean deleteUser(int userId) throws SQLException;
	} 


