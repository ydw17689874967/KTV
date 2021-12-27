package com.tjetc.common;

import java.time.LocalDate;
import java.util.List;

public class ProjectUserCountVo {

    private List<LocalDate> userDataTimes;

    private List<Long> userDataCounts;

    private List<LocalDate> projectsDataTimes;

    private List<Long> projectsDataCounts;

    public ProjectUserCountVo() {
    }

    public ProjectUserCountVo(List<LocalDate> userDataTimes, List<Long> userDataCounts, List<LocalDate> projectsDataTimes, List<Long> projectsDataCounts) {
        this.userDataTimes = userDataTimes;
        this.userDataCounts = userDataCounts;
        this.projectsDataTimes = projectsDataTimes;
        this.projectsDataCounts = projectsDataCounts;
    }

    public List<LocalDate> getUserDataTimes() {
        return userDataTimes;
    }

    public void setUserDataTimes(List<LocalDate> userDataTimes) {
        this.userDataTimes = userDataTimes;
    }

    public List<Long> getUserDataCounts() {
        return userDataCounts;
    }

    public void setUserDataCounts(List<Long> userDataCounts) {
        this.userDataCounts = userDataCounts;
    }

    public List<LocalDate> getProjectsDataTimes() {
        return projectsDataTimes;
    }

    public void setProjectsDataTimes(List<LocalDate> projectsDataTimes) {
        this.projectsDataTimes = projectsDataTimes;
    }

    public List<Long> getProjectsDataCounts() {
        return projectsDataCounts;
    }

    public void setProjectsDataCounts(List<Long> projectsDataCounts) {
        this.projectsDataCounts = projectsDataCounts;
    }
}
