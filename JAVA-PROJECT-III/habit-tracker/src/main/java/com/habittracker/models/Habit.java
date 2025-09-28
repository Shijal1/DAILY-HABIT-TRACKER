package com.habittracker.models;

import java.time.LocalTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Habit {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<LocalTime> reminderTime = new SimpleObjectProperty<>();

    public Habit(String name, String description) {
        this.name.set(name);
        this.description.set(description);
    }

    public String getName() { return name.get(); }
    public void setName(String n) { name.set(n); }
    public StringProperty nameProperty() { return name; }

    public String getDescription() { return description.get(); }
    public void setDescription(String d) { description.set(d); }
    public StringProperty descriptionProperty() { return description; }

    public LocalTime getReminderTime() { return reminderTime.get(); }
    public void setReminderTime(LocalTime t) { reminderTime.set(t); }
    public ObjectProperty<LocalTime> reminderTimeProperty() { return reminderTime; }
}
