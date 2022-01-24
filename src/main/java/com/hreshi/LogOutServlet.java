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



public class LogOutServlet extends HttpServlet { 

	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException {
		if (req.isRequestedSessionIdValid()) {
			HttpSession session = req.getSession(false);
			session.invalidate();
		}
		res.sendRedirect("signin");
	}

}