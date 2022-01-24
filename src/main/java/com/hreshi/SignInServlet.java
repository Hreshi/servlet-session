package com.hreshi;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.sql.SQLException;


public class SignInServlet extends HttpServlet { 
	
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.isRequestedSessionIdValid()) {
			res.sendRedirect("home");
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/static/html/signin.html");
			dispatcher.forward(req, res);
		}
	}

	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.isRequestedSessionIdValid()) {
			res.sendRedirect("home");
		} else {
			String name = (String) req.getParameter("username");
			String pass = (String) req.getParameter("password");
			if (checkCred(name, pass)) {
				HttpSession session = req.getSession();
				session.setAttribute("user", name);
				res.sendRedirect("home");
			} else {
				res.sendRedirect("signin");
			}
		}

	}

	private boolean checkCred(String name, String pass) {
		return Users.signInService(name, pass);
	}

}