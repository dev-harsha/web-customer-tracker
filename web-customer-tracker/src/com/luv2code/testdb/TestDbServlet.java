package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// setup connection variables
		String user = "root";
		String password = "root";
		
		String jdbcUrl = "jdbc:mysql://localhost:4444/web_customer_tracker?useSSL=false";
		
		String driver = "com.mysql.jdbc.Driver";
		
		// get connection to database
		try {
			
			PrintWriter out = response.getWriter();
			out.println("Connecting to database: " + jdbcUrl + "\n");
			
			Class.forName(driver);
			
			Connection myConnection = DriverManager.getConnection(jdbcUrl, user, password);
			
			out.println("\nSUCCESS!!!");
			
			myConnection.close();
			
		} catch (Exception e) {

			e.printStackTrace();
			throw new ServletException(e);
			
		}
		
		
	}

}
