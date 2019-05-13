package com.example.labs12_wellness_bet_sleep_android.Models;

import java.util.List;

public class ParticipantResponse {

    public int id;
    public int userId;
    public String groupName;
    public double buyInAmt;
    public String startDate;
    public String endDate;
    public String joinCode;
    public String groupMessage;
    public List<Participant> participant;

    public ParticipantResponse() {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public double getBuyInAmt() {
        return buyInAmt;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public String getGroupMessage() {
        return groupMessage;
    }

    public List<Participant> getParticipant() {
        return participant;
    }
}
