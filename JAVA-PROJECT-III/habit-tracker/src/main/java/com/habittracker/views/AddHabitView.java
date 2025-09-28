package com.habittracker.views;

import java.time.LocalTime;

import com.habittracker.models.Habit;
import com.habittracker.utils.HabitManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddHabitView extends VBox {

    public AddHabitView() {
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);

        Label title = new Label("➕ Add a New Habit");
        title.getStyleClass().add("header-label");

        TextField nameField = new TextField();
        nameField.setPromptText("Habit Name");

        TextField descField = new TextField();
        descField.setPromptText("Habit Description");

        Label timeLabel = new Label("Set Reminder Time:");
        ComboBox<Integer> hourBox = new ComboBox<>();
        ComboBox<Integer> minuteBox = new ComboBox<>();
        for (int i = 0; i < 24; i++) hourBox.getItems().add(i);
        for (int i = 0; i < 60; i++) minuteBox.getItems().add(i);
        hourBox.setValue(12);
        minuteBox.setValue(0);

        HBox timePicker = new HBox(10, timeLabel, hourBox, new Label(":"), minuteBox);
        timePicker.setAlignment(Pos.CENTER);

        ImageView addIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/add.png")));
        addIcon.setFitHeight(20);
        addIcon.setFitWidth(20);

        Button addButton = new Button("Add Habit", addIcon);
        addButton.getStyleClass().add("button");

        Label status = new Label();
        status.getStyleClass().add("status-label");

        addButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String desc = descField.getText().trim();
            int hour = hourBox.getValue();
            int minute = minuteBox.getValue();

            if (!name.isEmpty()) {
                Habit habit = new Habit(name, desc);
                habit.setReminderTime(LocalTime.of(hour, minute));
                HabitManager.addHabit(habit);

                status.setText("✅ Habit added!");
                status.getStyleClass().removeAll("status-error");
                status.getStyleClass().add("status-success");

                nameField.clear();
                descField.clear();
            } else {
                status.setText("⚠️ Please enter a name.");
                status.getStyleClass().removeAll("status-success");
                status.getStyleClass().add("status-error");
            }
        });

        this.getChildren().addAll(title, nameField, descField, timePicker, addButton, status);
    }
}
