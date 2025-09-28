package com.habittracker.views;

import com.habittracker.models.Habit;
import com.habittracker.utils.HabitManager;

import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ViewHabitsView extends VBox {

    private final ListView<String> habitList;

    public ViewHabitsView() {
        this.setPadding(new Insets(20));
        this.setSpacing(15);

        habitList = new ListView<>();
        habitList.setPrefHeight(400);

        // Dynamically update list
        HabitManager.getHabits().addListener((javafx.collections.ListChangeListener<Habit>) change -> refresh());
        refresh();

        this.getChildren().add(habitList);
    }

    private void refresh() {
        habitList.getItems().clear();
        for (Habit habit : HabitManager.getHabits()) {
            String reminder = habit.getReminderTime() != null ? " (Reminder: " + habit.getReminderTime() + ")" : "";
            habitList.getItems().add(habit.getName() + " - " + habit.getDescription() + reminder);
        }
    }
}
