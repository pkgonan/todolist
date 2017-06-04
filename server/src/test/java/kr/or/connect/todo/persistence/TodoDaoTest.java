package kr.or.connect.todo.persistence;

import kr.or.connect.todo.TodoApplication;
import kr.or.connect.todo.domain.Todo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
public class TodoDaoTest {

    @Autowired
    private TodoDao dao;

    @Test
    public void shouldInsertAndSelect(){
        // given
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Todo todo = new Todo("Java 웹개발", 0, time);

        // when
        Integer id = dao.insert(todo);

        // then
        Todo selected = dao.selectById(id);
        assertThat(selected.getTodo(), is("Java 웹개발"));
    }

    @Test
    public void shouldDelete(){
        // given
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Todo todo = new Todo("네이버 자바", 1, time);
        Integer id = dao.insert(todo);

        // when
        int affected = dao.deleteById(id);

        // Then
        assertThat(affected, is(1));
    }

    @Test
    public void shouldSelectAll(){
        List<Todo> todos = dao.selectAll();
        assertThat(todos, is(notNullValue()));
    }
}