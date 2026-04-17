package com.bhavitha.taskmanager.service;

import com.bhavitha.taskmanager.entity.Task;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class ReminderService {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(5);

    private final Map<String, ScheduledFuture<?>> reminders =
            new ConcurrentHashMap<>();

    public void scheduleReminder(Task task) {

        if (task.getDueDate() == null) {
            return;
        }

        cancelReminder(task.getId());

        LocalDateTime reminderTime = task.getDueDate().minusHours(1);

        long delay = Duration.between(
                LocalDateTime.now(),
                reminderTime
        ).toMillis();

        if (delay < 0) {
            delay = 0;
        }

        ScheduledFuture<?> future = scheduler.schedule(() -> {

            System.out.println("=================================");
            System.out.println("TASK REMINDER");
            System.out.println("Task ID: " + task.getId());
            System.out.println("Title: " + task.getTitle());
            System.out.println("User ID: " + task.getUserId());
            System.out.println("Due Date: " + task.getDueDate());
            System.out.println("=================================");

        }, delay, TimeUnit.MILLISECONDS);

        reminders.put(task.getId(), future);
    }

    public void cancelReminder(String taskId) {

        ScheduledFuture<?> future = reminders.get(taskId);

        if (future != null) {
            future.cancel(false);
            reminders.remove(taskId);
        }
    }
}