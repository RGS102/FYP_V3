package com.example.rossg_000.fyp_v3;

/**
 * Created by rossg_000 on 21/01/2018.
 */

public class JournalDetails {
    private int Id;
    private String taskName;
    private int taskRequirementInteger;
    private String taskRequirementString;
    private int taskLevelInteger;
    private int attempts;
    private String upOrDown;
    private String time;
    private String date;

    public JournalDetails(int Id, String taskName, int taskRequirementInteger, String taskRequirementString, int taskLevelInteger, int attempts, String upOrDown, String time, String date){
        this.Id = Id;
        this.taskName = taskName;
        this.taskRequirementInteger = taskRequirementInteger;
        this.taskRequirementString = taskRequirementString;
        this.taskLevelInteger = taskLevelInteger;
        this.attempts = attempts;
        this.upOrDown = upOrDown;
        this.time = time;
        this.date = date;
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

    public int getTaskLevelInteger() {
        return taskLevelInteger;
    }

    public void setTaskLevelInteger(int taskLevelInteger) {
        this.taskLevelInteger = taskLevelInteger;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(String upOrDown) {
        this.upOrDown = upOrDown;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


