package com.hreshi;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.IOException;

public class NameService extends HttpServlet{

	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.isRequestedSessionIdValid()) {
			String name = (String) req.getSession().getAttribute("user");
			if (name == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				res.setContentType("application/json");
				PrintWriter pt = res.getWriter();
				pt.print("{\"username\":\"" + name + "\"}");
				pt.flush();	
			}

		} else {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
}