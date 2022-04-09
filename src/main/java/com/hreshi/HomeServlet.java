package com.hreshi;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HomeServlet extends HttpServlet { 
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.isRequestedSessionIdValid()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/static/html/home.html");
			dispatcher.forward(req, res);
		} else {
			res.sendRedirect("signin");
		}
	}

}