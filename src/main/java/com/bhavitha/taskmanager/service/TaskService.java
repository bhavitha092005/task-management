package com.bhavitha.taskmanager.service;

import com.bhavitha.taskmanager.dto.TaskRequest;
import com.bhavitha.taskmanager.entity.Task;
import com.bhavitha.taskmanager.exception.CustomException;
import com.bhavitha.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    public Task createTask(TaskRequest request, Long userId) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        task.setUserId(userId);

        return taskRepo.save(task);
    }

    public List<Task> getAllTasks(Long userId) {
        return taskRepo.findByUserId(userId);
    }

    public Task getTaskById(String id, Long userId) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new CustomException("Task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new CustomException("Forbidden");
        }

        return task;
    }

    public Task updateTask(String id, TaskRequest request, Long userId) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new CustomException("Task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new CustomException("Forbidden");
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        return taskRepo.save(task);
    }

    public void deleteTask(String id, Long userId) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new CustomException("Task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new CustomException("Forbidden");
        }

        taskRepo.delete(task);
    }
}