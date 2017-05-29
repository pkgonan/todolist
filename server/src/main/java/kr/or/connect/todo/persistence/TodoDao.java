package kr.or.connect.todo.persistence;

import kr.or.connect.todo.domain.Todo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Todo> rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);

	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingGeneratedKeyColumns("id");
	}

	public Integer insert(Todo todo){
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return insertAction.executeAndReturnKey(params).intValue();
	}

	public List<Todo> select(){
		return jdbc.query(TodoSqls.SELECT_ALL, rowMapper);
	}

	public int update(Integer id){
		Todo findId = selectOne(id);
		findId.reverseCompleted();
		SqlParameterSource params = new BeanPropertySqlParameterSource(findId);
		return jdbc.update(TodoSqls.UPDATE_BY_ID, params);
	}

	public Todo selectOne(Integer id){
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(TodoSqls.SELECT_ONE, params, rowMapper);
	}

	public int delete(Integer id){
		Todo findId = selectOne(id);
		SqlParameterSource params = new BeanPropertySqlParameterSource(findId);
		return jdbc.update(TodoSqls.DELETE_BY_ID, params);
	}

	public int delete(){
		Map<String, ?> params = Collections.singletonMap("completed", 1);
		return jdbc.update(TodoSqls.DELETE_COMPLETE, params);
	}
}
