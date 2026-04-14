package com.bhavitha.taskmanager.controller;

import com.bhavitha.taskmanager.dto.TaskRequest;
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
                        HttpServletRequest httpRequest) {

        Long userId = (Long) httpRequest.getAttribute("userId");
        return taskService.getTaskById(id, userId);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id,
                           @Valid @RequestBody TaskRequest request,
                           HttpServletRequest httpRequest) {

        Long userId = (Long) httpRequest.getAttribute("userId");
        return taskService.updateTask(id, request, userId);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id,
                             HttpServletRequest httpRequest) {

        Long userId = (Long) httpRequest.getAttribute("userId");
        taskService.deleteTask(id, userId);
        return "Task deleted";
    }
}