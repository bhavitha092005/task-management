package com.bhavitha.taskmanager.repository;

import com.bhavitha.taskmanager.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByUserId(Long userId);

    List<Task> findByUserIdAndCategory(Long userId, String category);

    List<Task> findByUserIdAndTagsContaining(Long userId, String tag);

    List<Task> findByUserIdAndCategoryAndTagsContaining(
            Long userId,
            String category,
            String tag
    );
}