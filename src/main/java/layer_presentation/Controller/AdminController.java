package layer_presentation.Controller;

import com.itextpdf.text.DocumentException;
import dto.UserDTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import layer_business.Functions;
import dto.TennisMatchDTO;
import layer_business.MatchReport;
import layer_business.MatchReportFactory;
import layer_presentation.util.AlertBox;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminController {
    Functions f = new Functions();

    @FXML
    public Button newUserButton;

    @FXML
    public Button updateUserButton;

    @FXML
    public Button deleteUserButton;

    @FXML
    public Button newMatchButton;

    @FXML
    public Button updateMatchButton;

    @FXML
    public Button deleteMatchButton;

    @FXML
    public Button matchReportButton;

    @FXML
    public TextField mail;

    @FXML
    public TextField password;

    @FXML
    public TextField name;

    @FXML
    public TextField player1ID;

    @FXML
    public TextField player2ID;

    @FXML
    public TextField matchReportType;

    @FXML
    public TableView readingTable;

    public void readUsers() {

        TableColumn id = new TableColumn("ID");
        TableColumn email = new TableColumn("Email");
        TableColumn name = new TableColumn("Name");
        TableColumn password = new TableColumn("Password");

        readingTable.getColumns().clear();
        readingTable.getColumns().addAll(id, email, name, password);

        id.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("id"));
        email.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("mail"));
        name.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("name"));
        password.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("password"));

        ObservableList<UserDTO> data = f.requestUsers();
        readingTable.setItems(data);
    }

    public void createNewUser(){
        String userName = name.getText();
        String userPassword = password.getText();
        String userMail = mail.getText();

        f.requestCreateUser(userMail, userPassword, userName);
        readUsers();
    }

    public void updateUser(){
        UserDTO u = (UserDTO)readingTable.getSelectionModel().getSelectedItem();

        String updatedName = name.getText();
        String updatedPassword = password.getText();
        String updatedMail = mail.getText();

        f.requestUpdateUser(u.getId(), updatedMail, updatedPassword, updatedName);
        readUsers();
    }

    public void deleteUser(){
        UserDTO u = (UserDTO) readingTable.getSelectionModel().getSelectedItem();
        f.requestDeleteUser(u.getId());
        readUsers();
    }

    public void readMatches() {

        TableColumn id = new TableColumn("ID");
        TableColumn player1 = new TableColumn("Player 1");
        TableColumn player2 = new TableColumn("Player 2");

        readingTable.getColumns().clear();
        readingTable.getColumns().addAll(id, player1, player2);

		id.setCellValueFactory(new PropertyValueFactory<TennisMatchDTO, String>("id"));
        player1.setCellValueFactory(new PropertyValueFactory<TennisMatchDTO, UserDTO>("player1"));
        player2.setCellValueFactory(new PropertyValueFactory<TennisMatchDTO, UserDTO>("player2"));

        ObservableList<TennisMatchDTO> data = f.requestMatches();
        readingTable.setItems(data);
    }

    public void createMatch(){
        int player1ID_int = Integer.parseInt(player1ID.getText());
        int player2ID_int = Integer.parseInt(player2ID.getText());
        if(player1ID_int == player2ID_int) {
            AlertBox.display("Same player!", "A player cannot play against themselves!!!");
        }

        else    {
        f.requestCreateMatch(player1ID_int, player2ID_int);
        readMatches();
        }
    }


    public void updateMatch(){
        TennisMatchDTO t = (TennisMatchDTO) readingTable.getSelectionModel().getSelectedItem();

        int requestReturn = f.requestUpdateMatch(t.getId(), player1ID.getText(), player2ID.getText());
        if(requestReturn==-1) AlertBox.display("Same player!", "Same player cannot play against themselves!!!");
        readMatches();
    }

    public void deleteMatch(){
        TennisMatchDTO t = (TennisMatchDTO) readingTable.getSelectionModel().getSelectedItem();

        f.requestDeleteMatch(t.getId());
        readMatches();
    }

    public void generateMatchReport(){
        TennisMatchDTO t = (TennisMatchDTO) readingTable.getSelectionModel().getSelectedItem();

        MatchReportFactory factory = new MatchReportFactory();

        MatchReport report =  factory.create(t, matchReportType.getText());
        try {
            report.generate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
