package kr.or.connect.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Integer id;
    private String todo;
    private Integer completed;
    private Timestamp date;

    public Todo(String todo, Integer completed, Timestamp date){
        this.todo = todo;
        this.completed = completed;
        this.date = date;
    }

    public void reverseCompleted(){
        this.completed = this.completed == 0 ? 1 : 0;
    }
}