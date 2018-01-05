package com.example.rossg_000.fyp_v3;

/**
 * Created by rossg_000 on 05/01/2018.
 */

public class TaskDetails {
    private int Id;
    private String taskName;
    private int taskRequirementInteger;
    private String taskRequirementString;
    private String taskLevelString;
    private int taskLevelInteger;

    public TaskDetails(int Id, String taskName, int taskRequirementInteger, String taskRequirementString, String taskLevelString, int taskLevelInteger) {
        this.Id = Id;
        this.taskName = taskName;
        this.taskRequirementInteger = taskRequirementInteger;
        this.taskRequirementString = taskRequirementString;
        this.taskLevelString = taskLevelString;
        this.taskLevelInteger = taskLevelInteger;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskRequirementInteger() {
        return taskRequirementInteger;
    }

    public void setTaskRequirementInteger(int taskRequirementInteger) {
        this.taskRequirementInteger = taskRequirementInteger;
    }

    public String getTaskRequirementString() {
        return taskRequirementString;
    }

    public void setTaskRequirementString(String taskRequirementString) {
        this.taskRequirementString = taskRequirementString;
    }

    public String getTaskLevelString() {
        return taskLevelString;
    }

    public void setTaskLevelString(String taskLevelString) {
        this.taskLevelString = taskLevelString;
    }

    public int getTaskLevelInteger() {
        return taskLevelInteger;
    }

    public void setTaskLevelInteger(int taskLevelInteger) {
        this.taskLevelInteger = taskLevelInteger;
    }
}
