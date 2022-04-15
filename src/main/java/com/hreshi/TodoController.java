package com.hreshi;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.List;

public class TodoController extends HttpServlet{
	TodoService service = null;
	
	public void init () {
		service = new TodoService();
	}

	private String toJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	private Object fromJson(String json, Class clazz) {
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}

	private void writeToClient(HttpServletResponse res, String response) throws IOException{
		try (PrintWriter writer = res.getWriter()) {
			writer.write(response);
		}
	}
	
	private String readDataFromBody (BufferedReader reader) throws IOException{
		StringBuilder str = new StringBuilder();
		while(true) {
			String line = reader.readLine();
			if (line == null) break;
			str.append(line);
		}
		return str.toString();
	}
	// return all todo
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		if (req.isRequestedSessionIdValid()) {
			res.setContentType("application/json");
			String username = (String) req.getSession().getAttribute("user");
			List<Todo> todoList = service.getAllTasks(username);
			try (PrintWriter writer = res.getWriter()) {
				writer.write(toJson(todoList));
			}
		} else {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private Todo getTodoFromRequestBody(HttpServletRequest req) throws IOException{
		try (BufferedReader reader = req.getReader()) {
			String response = readDataFromBody(reader);
			System.out.println(response);
			return (Todo) fromJson(response, Todo.class);
		}
	}
	// add a todo
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		if (req.isRequestedSessionIdValid()) {
			String username = (String) req.getSession().getAttribute("user");
			Todo task = getTodoFromRequestBody(req);
			task.id = service.addTaskAndGetId(username, task.title);
			if (task.id != -1) {
				res.setStatus(HttpServletResponse.SC_OK);
				res.setContentType("application/json");
				writeToClient(res, toJson(task));
			} else {
				res.setStatus(404);
			}
		} else {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}	
	}
	// delete a todo using id
	public void doDelete (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		if (req.isRequestedSessionIdValid()) {
			int id = Integer.parseInt(req.getParameter("id"));
			if (service.removeTask(id)) {
				res.setStatus(HttpServletResponse.SC_OK);
			} else {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
	// update a task
	public void doPut (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.isRequestedSessionIdValid()) {
			Todo todo = (Todo) fromJson(readDataFromBody(req.getReader()), Todo.class);
			if (service.updateTask(todo)) {
				res.setStatus(HttpServletResponse.SC_OK);
			} else {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

}