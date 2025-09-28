package com.habittracker.views;

import java.time.LocalTime;

import com.habittracker.models.Habit;
import com.habittracker.utils.HabitManager;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeView extends VBox {

    private Label totalHabitsLabel;
    private Label upcomingRemindersLabel;

    public HomeView() {
        this.setPadding(new Insets(20));
        this.setSpacing(20);

        Label title = new Label("🌱 Habit Tracker Dashboard");
        title.getStyleClass().add("header-label");

        HBox statsBox = new HBox(20);
        statsBox.setAlignment(Pos.CENTER);

        // Total Habits
        VBox totalHabitsBox = createStatBox("Total Habits", "home.png");
        totalHabitsLabel = (Label) totalHabitsBox.getChildren().get(2); // the count label

        // Upcoming Reminders
        VBox upcomingRemindersBox = createStatBox("Upcoming Reminders", "reminder.png");
        upcomingRemindersLabel = (Label) upcomingRemindersBox.getChildren().get(2);

        statsBox.getChildren().addAll(totalHabitsBox, upcomingRemindersBox);

        this.getChildren().addAll(title, statsBox);

        // Listen to changes in habits list
        HabitManager.getHabits().addListener((ListChangeListener<Habit>) change -> updateStats());

        // Initial update
        updateStats();
    }

    private VBox createStatBox(String labelText, String imageName) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.getStyleClass().add("stat-box");

        ImageView icon;
        try {
            icon = new ImageView(new Image(getClass().getResourceAsStream("/images/" + imageName)));
        } catch (Exception e) {
            icon = new ImageView(); // fallback if image not found
        }
        icon.setFitHeight(50);
        icon.setFitWidth(50);

        Label label = new Label(labelText);
        Label countLabel = new Label("0");
        countLabel.getStyleClass().add("count-label");

        box.getChildren().addAll(icon, label, countLabel);
        return box;
    }

    private void updateStats() {
        totalHabitsLabel.setText(String.valueOf(HabitManager.getHabits().size()));
        upcomingRemindersLabel.setText(String.valueOf(countUpcomingReminders()));
    }

    private int countUpcomingReminders() {
        LocalTime now = LocalTime.now();
        int count = 0;
        for (Habit habit : HabitManager.getHabits()) {
            if (habit.getReminderTime() != null && habit.getReminderTime().isAfter(now)) {
                count++;
            }
        }
        return count;
    }
}
