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
            "WHERE user_id = ?2 AND project_id = ?3 AND id = ?4",
            nativeQuery = true)
    int updateByUserIdAndProjectIdAndId(Integer checkCode, Integer userId, Integer projectId, Integer sighUpInfoId);

    @Modifying
    @Query(value = "update sigh_up_info " +
            "SET check_code = ?1 " +
            "WHERE user_id = ?2 AND id = ?3",
            nativeQuery = true)
    int updateByUserIdAndId(Integer checkCode, Integer userId, Integer sighUpInfoId);

    List<SighUpInfo> findSighUpInfoListByProject(Project project);
    List<SighUpInfo> findAllByUser(User user);
}
