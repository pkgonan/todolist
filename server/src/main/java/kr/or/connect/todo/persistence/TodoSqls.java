package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id= :id";
	static final String SELECT_ALL =
			"SELECT * FROM todo";
	static final String SELECT_ONE =
			"SELECT id, todo, completed, date FROM todo WHERE id= :id";
	static final String UPDATE_BY_ID =
			"UPDATE todo SET completed= :completed WHERE id= :id";
	static final String DELETE_COMPLETE =
			"DELETE FROM todo WHERE completed= :completed";
}
