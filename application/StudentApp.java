package application;

import database.DBOperations;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Student;

public class StudentApp extends Application {

    private TextField txtId = new TextField();
    private TextField txtName = new TextField();
    private TextField txtMajor = new TextField();
    private TextField txtGrade = new TextField();
    private DBOperations db;

    @Override
    public void init() {
        db = new DBOperations();
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(30));
        inputGrid.setAlignment(Pos.CENTER);

        inputGrid.add(new Label("Student ID:"), 0, 0); inputGrid.add(txtId, 1, 0);
        inputGrid.add(new Label("Name:"), 0, 1); inputGrid.add(txtName, 1, 1);
        inputGrid.add(new Label("Major:"), 0, 2); inputGrid.add(txtMajor, 1, 2);
        inputGrid.add(new Label("Grade:"), 0, 3); inputGrid.add(txtGrade, 1, 3);

        Button btnAdd = new Button("Add Student");
        Button btnUpdate = new Button("Update");
        Button btnDelete = new Button("Delete");

        HBox buttonBox = new HBox(15, btnAdd, btnUpdate, btnDelete);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        VBox mainLayout = new VBox(10, inputGrid, buttonBox);
        mainLayout.setAlignment(Pos.CENTER);

        btnAdd.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                db.addStudent(new Student(id, txtName.getText(), txtMajor.getText(), Double.parseDouble(txtGrade.getText())));
                showAlert("Success", "Student added successfully!");
                clearFields();
            } catch (Exception ex) {
                showAlert("Error", "Check your input or DB connection.");
            }
        });

        btnDelete.setOnAction(e -> {
            try {
                db.deleteStudent(Integer.parseInt(txtId.getText()));
                showAlert("Deleted", "Student removed successfully!");
                clearFields();
            } catch (Exception ex) {
                showAlert("Error", "Check ID.");
            }
        });

        btnUpdate.setOnAction(e -> {
            try {
                db.updateStudent(new Student(Integer.parseInt(txtId.getText()), txtName.getText(),
                        txtMajor.getText(), Double.parseDouble(txtGrade.getText())));
                showAlert("Updated", "Data modified successfully!");
            } catch (Exception ex) {
                showAlert("Error", ex.getMessage());
            }
        });

        primaryStage.setScene(new Scene(mainLayout, 450, 350));
        primaryStage.setTitle("Student Management System");
        primaryStage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        txtId.clear(); txtName.clear(); txtMajor.clear(); txtGrade.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}