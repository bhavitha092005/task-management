package com.bhavitha.taskmanager.service;
import com.bhavitha.taskmanager.dto.TaskRequest;
import com.bhavitha.taskmanager.dto.UpdateTaskRequest;
import com.bhavitha.taskmanager.entity.Task;
import com.bhavitha.taskmanager.exception.CustomException;
import com.bhavitha.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.HashSet;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private WebhookService webhookService;

    public Task createTask(TaskRequest request, Long userId) {

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        task.setUserId(userId);
        task.setCategory(request.getCategory());
        task.setTags(request.getTags());

        Task savedTask = taskRepo.save(task);

        reminderService.scheduleReminder(savedTask);

        return savedTask;
    }

    public List<Task> getAllTasks(Long userId) {
        return taskRepo.findByUserId(userId);
    }

    public List<Task> getTasksByCategory(Long userId, String category) {
        return taskRepo.findByUserIdAndCategory(userId, category);
    }

    public List<Task> getTasksByTag(Long userId, String tag) {
        return taskRepo.findByUserIdAndTagsContaining(userId, tag);
    }

    public Task getTaskById(String id, Long userId) {

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new CustomException("Task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new CustomException("Forbidden");
        }

        return task;
    }

    public Task updateTask(String id, UpdateTaskRequest request, Long userId) {

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new CustomException("Task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new CustomException("Forbidden");
        }

        String oldStatus = task.getStatus();

        if (request.getTitle() != null)
            task.setTitle(request.getTitle());

        if (request.getDescription() != null)
            task.setDescription(request.getDescription());

        if (request.getStatus() != null)
            task.setStatus(request.getStatus());

        if (request.getDueDate() != null)
            task.setDueDate(request.getDueDate());

        if (request.getCategory() != null)
            task.setCategory(request.getCategory());

        if (request.getTags() != null)
            task.setTags(request.getTags());

        Task updatedTask = taskRepo.save(task);

        if ("completed".equalsIgnoreCase(updatedTask.getStatus())) {

            reminderService.cancelReminder(updatedTask.getId());

            if (!"completed".equalsIgnoreCase(oldStatus)) {
                webhookService.sendTaskCompleted(updatedTask);
            }

        } else {
            reminderService.scheduleReminder(updatedTask);
        }

        return updatedTask;
    }

    public void deleteTask(String id, Long userId) {

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new CustomException("Task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new CustomException("Forbidden");
        }

        reminderService.cancelReminder(id);

        taskRepo.delete(task);
    }
    
    public List<Task> filterTasks(Long userId, String category, String tag) {

        if (category != null && tag != null) {
            return taskRepo.findByUserIdAndCategoryAndTagsContaining(
                    userId, category, tag);
        }

        if (category != null) {
            return taskRepo.findByUserIdAndCategory(userId, category);
        }

        if (tag != null) {
            return taskRepo.findByUserIdAndTagsContaining(userId, tag);
        }

        return taskRepo.findByUserId(userId);
    }
    
    public Set<String> getAllTags(Long userId) {

        List<Task> tasks = taskRepo.findByUserId(userId);

        Set<String> tags = new HashSet<>();

        for (Task task : tasks) {
            if (task.getTags() != null) {
                tags.addAll(task.getTags());
            }
        }

        return tags;
    }
}