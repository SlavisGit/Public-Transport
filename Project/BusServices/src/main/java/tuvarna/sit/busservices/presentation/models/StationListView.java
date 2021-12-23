package tuvarna.sit.busservices.presentation.models;

import java.sql.Time;

public class StationListView {
    private String name;
    private String address;
    private Time workTimeStart;
    private Time workTimeEnd;

    public StationListView(String name, String address, Time workTimeStart, Time workTimeEnd) {
        this.name = name;
        this.address = address;
        this.workTimeStart = workTimeStart;
        this.workTimeEnd = workTimeEnd;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Time getWorkTimeStart() {
        return workTimeStart;
    }

    public Time getWorkTimeEnd() {
        return workTimeEnd;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s", name, address, workTimeStart.toString(), workTimeEnd.toString());
    }
}
