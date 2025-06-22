package com.github.hotechbackend.task.infrastructure.persistence.jpa.repositories;

import com.github.hotechbackend.task.domain.model.aggregates.Task;
import com.github.hotechbackend.task.domain.model.valueobjects.RoomIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByRoom(RoomIdentifier room);

    @Query("SELECT t FROM Task t WHERE t.room = :employee AND t.dueDate >= :start AND t.dueDate <= :end")
    List<Task> finAllInDateRangeByEmployee(RoomIdentifier employee, Date start, Date end);
}
