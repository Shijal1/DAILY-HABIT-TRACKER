package com.habittracker;

import com.habittracker.views.AddHabitView;
import com.habittracker.views.HomeView;
import com.habittracker.views.RemindersView;
import com.habittracker.views.SuggestionsView;
import com.habittracker.views.ViewHabitsView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        TabPane tabPane = new TabPane();

        tabPane.getTabs().addAll(
            createTab("🏠 Home", new HomeView()),
            createTab("➕ Add Habit", new AddHabitView()),
            createTab("📋 View Habits", new ViewHabitsView()),
            createTab("⏰ Reminders", new RemindersView()),
            createTab("💡 Suggestions", new SuggestionsView())
        );

        Scene scene = new Scene(tabPane, 900, 650);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("🌱 Habit Tracker");
        stage.show();
    }

    private Tab createTab(String title, VBox content) {
        Tab tab = new Tab(title);
        tab.setContent(content);
        tab.setClosable(false);
        return tab;
    }

    public static void main(String[] args) {
        launch();
    }
}
