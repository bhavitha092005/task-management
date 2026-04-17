package com.bhavitha.taskmanager.controller;

import com.bhavitha.taskmanager.dto.TaskRequest;
import com.bhavitha.taskmanager.dto.UpdateTaskRequest;
import com.bhavitha.taskmanager.entity.Task;
import com.bhavitha.taskmanager.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@Valid @RequestBody TaskRequest request,
                           HttpServletRequest httpRequest) {

        Long userId = (Long) httpRequest.getAttribute("userId");
        return taskService.createTask(request, userId);
    }

    @GetMapping
    public List<Task> getAllTasks(HttpServletRequest httpRequest) {
        Long userId = (Long) httpRequest.getAttribute("userId");
        return taskService.getAllTasks(userId);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable String id,
                        HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        return taskService.getTaskById(id, userId);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id,
                           @RequestBody UpdateTaskRequest request,
                           HttpServletRequest requestObj) {

        Long userId = (Long) requestObj.getAttribute("userId");
        return taskService.updateTask(id, request, userId);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id,
                             HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        taskService.deleteTask(id, userId);
        return "Task deleted";
    }
    
    @GetMapping("/filter")
    public List<Task> filterTasks(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return taskService.filterTasks(userId, category, tag);
    }
    
    @GetMapping("/tags")
    public Object getTags(HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return taskService.getAllTags(userId);
    }
}