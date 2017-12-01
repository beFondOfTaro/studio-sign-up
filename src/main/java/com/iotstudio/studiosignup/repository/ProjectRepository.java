package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Project findProjectByName(String name);
}
