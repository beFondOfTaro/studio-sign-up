package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.entity.Project;
import com.iotstudio.studiosignup.entity.SighUpInfo;
import com.iotstudio.studiosignup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SighUpInfoRepository extends JpaRepository<SighUpInfo,Integer> {
    SighUpInfo findByProject(Project project);
    void deleteByProject(Project project);
    void deleteByUser(User user);
}
