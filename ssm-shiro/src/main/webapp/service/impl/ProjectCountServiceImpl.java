package com.tjetc.service.impl;

import com.tjetc.common.ProjectUserCountVo;
import com.tjetc.dao.ProjectCountMapper;
import com.tjetc.entity.vo.ProjectTimeCount;
import com.tjetc.service.ProjectCountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProjectCountServiceImpl implements ProjectCountsService {

    @Autowired
    private ProjectCountMapper projectCountMapper;

    @Override
    public ProjectUserCountVo selectChartData() {
        List<ProjectTimeCount> userData = projectCountMapper.selectUserCounts();
        List<LocalDate> userTimes = new ArrayList<>();
        List<Long> userCounts = new ArrayList<>();
        Iterator<ProjectTimeCount> iterator = userData.iterator();
        while (iterator.hasNext()) {
            ProjectTimeCount next = iterator.next();
            userTimes.add(next.getTime());
            userCounts.add(next.getCounts());
        }

        List<ProjectTimeCount> projectData = projectCountMapper.selectProjectCounts();
        List<LocalDate> projectTimes = new ArrayList<>();
        List<Long> projectCounts = new ArrayList<>();
        Iterator<ProjectTimeCount> iteratorTwo = projectData.iterator();
        while (iteratorTwo.hasNext()) {
            ProjectTimeCount next = iteratorTwo.next();
            projectTimes.add(next.getTime());
            projectCounts.add(next.getCounts());
        }
        return new ProjectUserCountVo(userTimes, userCounts, projectTimes, projectCounts);
    }
}
