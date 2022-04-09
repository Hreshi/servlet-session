package com.hreshi;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.IOException;

public class TodoController extends HttpServlet{
	
	// return all todo
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		if (req.isRequestedSessionIdValid()) {

		} else {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
	// add a todo
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		if (req.isRequestedSessionIdValid()) {

		} else {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}	
	}
	// delete a todo using id
	public void doDelete (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		if (req.isRequestedSessionIdValid()) {

		} else {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
}