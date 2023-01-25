package com.endpoints.todoapp.mapper;

import com.endpoints.todoapp.persistence.entity.Task;
import com.endpoints.todoapp.persistence.entity.TaskStatus;
import com.endpoints.todoapp.service.dto.TaskinDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskinDTOtoTask implements  IMapper<TaskinDTO , Task>{

    @Override
    public Task map(TaskinDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
