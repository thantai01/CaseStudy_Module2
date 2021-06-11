package sample.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense {
    private String eventTime;
    private String eventName;
    private int eventCost;

    public Expense(String eventName, int eventCost) {
        this.eventTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.eventName = eventName;
        this.eventCost = eventCost;
    }

    public Expense() {
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventCost() {
        return eventCost;
    }

    public void setEventCost(int eventCost) {
        this.eventCost = eventCost;
    }

    @Override
    public String toString() {
        return
               ": Expense { " +
                "Time: " + eventTime +
                ", Event: " + eventName + '\'' +
                ", Cost: " + String.format("%,8d%n",eventCost) + "VND"+
                '}' + "\n";
    }
}
