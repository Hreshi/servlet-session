package com.hreshi;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

import java.util.List;
import java.util.ArrayList;

public class TodoService {
	Connection con = null;
	TodoService () {
		try {
			con = new Database().getConnection();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public List<Todo> getAllTasks(String username) {
		String query = "select taskid, title from todos where username=\"%s\"";
		List<Todo> todoList = new ArrayList<>();
			try {
				Statement stmt = con.createStatement();
				ResultSet result = stmt.executeQuery(String.format(query, username));
				while(result.next()) {
					Todo task = new Todo(result.getInt("taskid"), result.getString("title"));
					todoList.add(task);
				}
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		return todoList;
	}
	public int addTaskAndGetId(String username, String task) {
		String query = "insert into todos (username, title) values (\"%s\", \"%s\");";
		query = String.format(query, username, task);
		try (
			Statement stmt = con.createStatement();
		){
			stmt.executeUpdate(query);
			ResultSet result = stmt.executeQuery("select last_insert_id()");
			return result.next() ? result.getInt("last_insert_id()") : -1;
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	public boolean removeTask(int id) {
		String query = String.format ("delete from todos where taskid=\"%d\"", id);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	public boolean updateTask(Todo todo) {
		String query = String.format("update from todos set title=\"%s\" where taskid=\"%d\"", todo.title, todo.id);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;	
	}
}