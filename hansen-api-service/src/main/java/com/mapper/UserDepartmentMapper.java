package com.mapper;

import com.base.dao.CommonDao;
import com.model.UserDepartment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDepartmentMapper extends CommonDao<UserDepartment> {

    List<UserDepartment> getDirectTeamList(@Param("parentUserId") String parentUserId);

    Double getSumAmt(@Param("parentUserId") String parentUserId);

    Double getSumDeparmentPerformanceByParentUserId(@Param("parentUserId") String parentUserId);

    Integer updatePerformanceByUserId(@Param("userId")  String userId, @Param("performance")  Double performance);

    Integer updateTeamPerformanceByUserId(@Param("userId")  String userId, @Param("teamPerformance")  Double teamPerformance);

    Integer updateDeparmentAndTeamPerformanceByUserId(@Param("userId")  String userId,  @Param("performance")  Double performance,@Param("teamPerformance")  Double teamPerformance);

    List<UserDepartment> getAll(@Param("parentUserId") String parentUserId);

    List<UserDepartment> getAllUserDepartment();

    Integer getMaxGrade(@Param("parentUserId") String parentUserId);

    List<UserDepartment> getDirectMaxGradeList(@Param("parentUserId") String parentUserId);
}
