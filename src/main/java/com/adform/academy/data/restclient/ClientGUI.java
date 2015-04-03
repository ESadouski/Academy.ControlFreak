package com.adform.academy.data.restclient;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ClientGUI extends Application {
    RestClient client;
    final SchemaInfoFields infoBox = new SchemaInfoFields();

    @Override
    public void start(Stage primaryStage) throws Exception {
        client = new RestClient("http://localhost:8080/adf/v1/scheme");
        BorderPane root = new BorderPane();
        primaryStage.setScene(new Scene(root, 500, 500));

        ButtonBox buttonBox = new ButtonBox();
        root.setBottom(buttonBox);
        root.setTop(infoBox);
        BorderPane.setMargin(infoBox, new Insets(5, 0, 0, 20));
        BorderPane.setMargin(buttonBox, new Insets(5, 5, 5, 20));
        primaryStage.show();


    }

    public static void main(String[] args) {
        Application.launch();
    }


    class SchemaInfoFields extends VBox {
        SchemaInfoFields() {
            HBox groupBox = new HBox();
            HBox nameBox = new HBox();
            HBox versionBox = new HBox();
            Label groupLabel = new Label("Group name:");
            Label nameLabel = new Label("Scheme name:");
            Label versionLabel = new Label("Version:");
            TextField groupField = new TextField();
            TextField nameField = new TextField();
            TextField versionField = new TextField();
            groupBox.getChildren().addAll(groupLabel, groupField);
            nameBox.getChildren().addAll(nameLabel, nameField);
            versionBox.getChildren().addAll(versionLabel, versionField);
            groupLabel.setPrefWidth(100);
            nameLabel.setPrefWidth(100);
            versionLabel.setPrefWidth(100);
            this.setSpacing(5);
            this.getChildren().addAll(groupBox, nameBox, versionBox);
        }

    }

    class ButtonBox extends HBox {
        ButtonBox() {
            Button get = new Button("Get scheme");
            Button delete = new Button("Delete scheme");
            Button getLatest = new Button("Get latest scheme");
            this.getChildren().addAll(get, delete, getLatest);
            this.setSpacing(5);

            get.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });
        }

    }

}
