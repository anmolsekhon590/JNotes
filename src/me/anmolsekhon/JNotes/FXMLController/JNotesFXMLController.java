package me.anmolsekhon.JNotes.FXMLController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JNotesFXMLController implements Initializable {

    @FXML
    private Label lblheading;

    @FXML
    private TextArea txtNote;

    @FXML
    private Button btnSave, btnExit;

    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


//        Initializing
        datePicker.setValue(LocalDate.now());

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("./data/" + datePicker.getValue() + ".txt");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(fileReader);
        System.out.println(datePicker.getValue());
        String notes = "";
        while (scan.hasNextLine()) {
            notes = notes.concat(scan.nextLine() + "\n");
        }

        txtNote.setText(notes);

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Clicked Save Button");
                File dataDirectory = new File("data");

                if (!dataDirectory.exists()) {
                    System.out.println("Directory Does not Exist");
                    dataDirectory.mkdir();
                } else {
                    File noteFile = new File("./data/"+ LocalDate.now().toString()+ ".txt");
                    try {
                        FileWriter fileWriter = new FileWriter(noteFile);
                        BufferedWriter writer = new BufferedWriter(fileWriter);

                        writer.write(txtNote.getText());

                        writer.close();
                        fileWriter.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileReader fileReader = null;
                File file = new File("./data/" + datePicker.getValue() + ".txt");
                if (file.exists()) {
                    try {
                        fileReader = new FileReader(file);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    File fileNew = new File("./data/" + datePicker.getValue() + ".txt");
                    FileWriter fileWriter = null;
                    try {
                        fileWriter = new FileWriter(fileNew);
                        BufferedWriter writer = new BufferedWriter(fileWriter);
                        writer.write("");
                        writer.close();
                        fileWriter.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                Scanner scan = new Scanner(fileReader);
                System.out.println(datePicker.getValue());
                String notes = "";
                while (scan.hasNextLine()) {
                    notes = notes.concat(scan.nextLine() + "\n");
                }
                txtNote.setText(notes);
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileReader fileReader = null;
                try {
                    fileReader = new FileReader("./data/" + datePicker.getValue() + ".txt");

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                Scanner scan = new Scanner(fileReader);
                System.out.println(datePicker.getValue());
                String notes = "";
                while (scan.hasNextLine()) {
                    notes = notes.concat(scan.nextLine() + "\n");
                }

                if (!notes.equals(txtNote.getText())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING,"You have unsaved changes\nAre you sure you want to exit?", ButtonType.NO, ButtonType.YES);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.YES) {
                        System.exit(0);
                    } else {
                        alert.close();
                    }
                } else if(notes.equals(txtNote.getText()) || notes.equals(notes.equals(txtNote.getText() + "\n")))  {
                    System.exit(0);
                }
            }
        });
    }
}
