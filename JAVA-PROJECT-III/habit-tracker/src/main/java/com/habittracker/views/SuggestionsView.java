package com.habittracker.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SuggestionsView extends VBox {

    public SuggestionsView() {
        this.setPadding(new Insets(20));
        this.setSpacing(15);

        Label title = new Label("💡 Tips & Suggestions");
        title.getStyleClass().add("header-label");

        Label suggestion1 = new Label("✅ Start with small habits to build consistency.");
        Label suggestion2 = new Label("📝 Track your progress daily.");
        Label suggestion3 = new Label("⏰ Set reminders to stay on track.");

        this.getChildren().addAll(title, suggestion1, suggestion2, suggestion3);
    }
}
