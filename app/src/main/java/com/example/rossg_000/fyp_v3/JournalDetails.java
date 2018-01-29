package com.example.rossg_000.fyp_v3;

/**
 * Created by rossg_000 on 21/01/2018.
 */

public class JournalDetails {
    private int Id;
    private String taskName;
    private int taskRequirementInteger;
    private int taskLevelInteger;
    private int attempts;
    private String upOrDown;
    private String time;
    private String date;
    private String progressString;

    public JournalDetails(int Id, String taskName, int taskRequirementInteger, int taskLevelInteger, int attempts, String upOrDown, String time, String date, String progressString){
        this.Id = Id;
        this.taskName = taskName;
        this.taskRequirementInteger = taskRequirementInteger;
        this.taskLevelInteger = taskLevelInteger;
        this.attempts = attempts;
        this.upOrDown = upOrDown;
        this.time = time;
        this.date = date;
        this.progressString = progressString;
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

    public String getProgressString() {
        return progressString;
    }

    public void setProgressString(String progressString) {
        this.progressString = progressString;
    }
}


