package kr.or.connect.todo.service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoDao dao;

    public Todo insert(String data){
        Todo todo = new Todo(data, 0, new Timestamp(System.currentTimeMillis()));
        Integer id = dao.insert(todo);
        todo.setId(id);
        return todo;
    }

    public List<Todo> findAll(){
        return dao.selectAll();
    }

    public int update(Integer id){
        return dao.update(id);
    }

    public int delete(Integer id){
        return dao.deleteById(id);
    }

    public int delete(){
        return dao.deleteCompleted();
    }
}