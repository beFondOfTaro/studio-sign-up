package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.object.entity.Project;
import com.iotstudio.studiosignup.object.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Project findProjectByName(String name);

    List<Project> findAllByUser(User user);
}
