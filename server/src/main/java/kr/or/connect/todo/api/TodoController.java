package kr.or.connect.todo.api;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoService service;

    @PostMapping
    Todo post(@RequestParam String todo){
        return service.insert(todo);
    }

    @GetMapping
    List<Todo> get(){
        return service.select();
    }

    @PutMapping
    int put(@RequestParam Integer id){
        return service.update(id);
    }

    @DeleteMapping("/{id}")
    int delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @DeleteMapping
    int clear(){
        return service.delete();
    }
}