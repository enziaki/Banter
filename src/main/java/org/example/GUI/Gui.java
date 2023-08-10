package org.example.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javafx.concurrent.Task;

public class Gui extends Application {
    private Stage primaryStage;
    private ProgressBar progressBar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Banter 1.5-alpha");

        String systemIPAddress = getSystemIPAddress();

        TextField systemIPTextField = new TextField(systemIPAddress);
        TextField senderIPTextField = new TextField();
        TextArea fileTextArea = new TextArea();
        fileTextArea.setPromptText("Select a file...");

        Button fileSelectorButton = new Button("Browse");
        FileChooser fileChooser = new FileChooser();
        fileSelectorButton.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                fileTextArea.setText(selectedFile.getAbsolutePath());
            }
        });

        fileTextArea.setOnDragOver(event -> {
            if (event.getGestureSource() != fileTextArea && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        fileTextArea.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                File file = db.getFiles().get(0);
                fileTextArea.setText(file.getAbsolutePath());
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String senderIP = senderIPTextField.getText();
            if (isValidIPAddress(senderIP)) {
                System.out.println("Submission successful!");
                // openProgressBarWindow();
            } else {
                showAlert("Invalid IP Address", "Please enter a valid sender IP address.");
            }
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(
                new Label("System IP Address:"),
                systemIPTextField,
                new Label("Sender IP Address:"),
                senderIPTextField,
                new Label("Select a File:"),
                new HBox(fileTextArea),
                new HBox(fileSelectorButton,submitButton)

        );

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getSystemIPAddress() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    // private void openProgressBarWindow() {
    //     Stage progressBarStage = new Stage();
    //     progressBarStage.setTitle("Progress");

    //     progressBar = new ProgressBar(0);
    //     progressBar.setProgress(0);
    //     progressBar.setMaxWidth(Double.MAX_VALUE);


    //     Button cancelButton = new Button("Cancel");
    //     cancelButton.setOnAction(e -> {
    //         progressBarStage.close();
    //     });

    //     // Incase the Trasfer fails we can use this to resend it
    //     Button retryButton = new Button("Retry");
    //     retryButton.setOnAction(e -> {
    //         progressBar.setProgress(0);

    //     });

    //     VBox progressVBox = new VBox(10);
    //     progressVBox.setPadding(new Insets(20));
    //     progressVBox.getChildren().addAll(progressBar, new HBox(cancelButton, retryButton));

    //     Scene progressScene = new Scene(progressVBox, 300, 150);
    //     progressBarStage.setScene(progressScene);

    //     Task<Void> task = new Task<Void>() {
    //         @Override
    //         //Random Progrees work
    //         protected Void call() throws Exception {
    //             for (int i = 0; i <= 100; i++) {
    //                 Thread.sleep(50);
    //                 updateProgress(i, 100);
    //             }
    //             return null;
    //         }
    //     };

    //     progressBar.progressProperty().bind(task.progressProperty());
    //     cancelButton.disableProperty().bind(task.runningProperty());
    //     retryButton.disableProperty().bind(task.runningProperty());

    //     progressBarStage.setOnShown(event -> new Thread(task).start());
    //     progressBarStage.show();
    // }

    private boolean isValidIPAddress(String ipAddress) {
        String[] parts = ipAddress.split("\\.");

        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {
            try {
                int value = Integer.parseInt(part);

                if (value < 0 || value > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    } 
}
