package kr.or.connect.todo.api;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    TodoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Todo post(@RequestParam String todo){
        return service.insert(todo);
    }

    @GetMapping
    List<Todo> get(){
        return service.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    int put(@RequestParam Integer id){
        return service.update(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    int delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    int clear(){
        return service.delete();
    }
}