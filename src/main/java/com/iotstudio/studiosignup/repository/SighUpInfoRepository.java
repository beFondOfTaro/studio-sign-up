package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.object.entity.Project;
import com.iotstudio.studiosignup.object.entity.SighUpInfo;
import com.iotstudio.studiosignup.object.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SighUpInfoRepository extends JpaRepository<SighUpInfo,Integer> {
    List<SighUpInfo> findSighUpInfoListByUserAndProject(User user, Project project);
    void deleteByProject(Project project);
    void deleteByUser(User user);

    @Modifying
    @Query(value = "update sigh_up_info " +
            "SET check_code = ?1 " +
            "WHERE user_id = ?2 AND project_id = ?3 AND sigh_up_info_id = ?4",
            nativeQuery = true)
    int updateByUserIdAndProjectId(Integer checkCode, Integer userId, Integer projectId, Integer sighUpInfoId);

    List<SighUpInfo> findSighUpInfoListByProject(Project project);
    List<SighUpInfo> findAllByUser(User user);
}
