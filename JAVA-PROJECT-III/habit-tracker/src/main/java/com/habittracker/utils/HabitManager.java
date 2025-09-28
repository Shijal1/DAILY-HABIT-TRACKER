package com.habittracker.utils;

import com.habittracker.models.Habit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HabitManager {
    private static final ObservableList<Habit> habits = FXCollections.observableArrayList();

    public static ObservableList<Habit> getHabits() { return habits; }
    public static void addHabit(Habit habit) { habits.add(habit); }
}
