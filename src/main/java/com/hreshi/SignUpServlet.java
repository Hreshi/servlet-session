package com.hreshi;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;



public class SignUpServlet extends HttpServlet { 
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.isRequestedSessionIdValid()) {
			res.sendRedirect("home");
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/static/html/signup.html");
			dispatcher.forward(req, res);			
		}
	}

	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException { 
		if (req.isRequestedSessionIdValid()) {
			res.sendRedirect("home");
		} else {
			String name = (String) req.getParameter("username");
			String pass = (String) req.getParameter("password");
			if (Users.signUpService(name, pass)) {
				HttpSession session = req.getSession();
				session.setAttribute("user", name);
				res.sendRedirect("home");
			} else {
				res.sendRedirect("signup");
			}
		}
	}

}