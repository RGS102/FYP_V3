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
    private String Recommendations;

    /*
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    */
    public TaskDetails(int Id, String taskName, int taskRequirementInteger, String taskRequirementString, String taskLevelString, int taskLevelInteger, String Recommendations){//, int days, int hours, int minutes, int seconds) {
        this.Id = Id;
        this.taskName = taskName;
        this.taskRequirementInteger = taskRequirementInteger;
        this.taskRequirementString = taskRequirementString;
        this.taskLevelString = taskLevelString;
        this.taskLevelInteger = taskLevelInteger;
        this.Recommendations = Recommendations;
        /*
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        */
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

    public String getRecommendations() {
        return Recommendations;
    }

    public void setRecommendations(String Recommendations) {
        this.Recommendations = Recommendations;
    }

/*
    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    */
}
