package com.habittracker.views;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.habittracker.models.Habit;
import com.habittracker.utils.HabitManager;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class RemindersView extends VBox {

    private final TableView<Habit> reminderTable;

    public RemindersView() {
        this.setPadding(new Insets(20));
        this.setSpacing(15);

        reminderTable = new TableView<>();
        reminderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Habit, String> nameCol = new TableColumn<>("Habit");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Habit, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Habit, LocalTime> timeCol = new TableColumn<>("Reminder Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("reminderTime"));

        reminderTable.getColumns().addAll(nameCol, descCol, timeCol);
        this.getChildren().add(reminderTable);

        // Refresh and check reminders every 30 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30), e -> refreshReminders()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        refreshReminders();
    }

    private void refreshReminders() {
        LocalTime now = LocalTime.now().withSecond(0).withNano(0);

        List<Habit> upcoming = HabitManager.getHabits().stream()
                .filter(h -> h.getReminderTime() != null)
                .collect(Collectors.toList());

        reminderTable.setItems(FXCollections.observableArrayList(upcoming));

        // Trigger popups for exact match
        for (Habit habit : upcoming) {
            if (habit.getReminderTime().equals(now)) {
                showReminderPopup(habit);
            }
        }
    }

    private void showReminderPopup(Habit habit) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Habit Reminder");
        alert.setHeaderText("⏰ Reminder: " + habit.getName());
        alert.setContentText(habit.getDescription());
        alert.show();
    }
}
