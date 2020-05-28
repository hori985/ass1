package layer_presentation.Controller;

import dto.TennisMatchDTO;
import dto.TennisSetDTO;
import dto.UserDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import layer_business.TennisService;
import model.TennisMatch;

import java.util.ArrayList;


public class UserController {
    TennisService tennisService = new TennisService();

    @FXML
    public TableView readingTable;

    @FXML
    public Button viewSetsButton;

    @FXML
    public Button viewMatchesButton;

    @FXML
    public Button startMatchButton;

    @FXML
    public TextField matchID;

    public void viewMatches(ActionEvent actionEvent) {
        TableColumn id = new TableColumn("ID");
        TableColumn player1 = new TableColumn("Player 1");
        TableColumn player2 = new TableColumn("Player 2");

        readingTable.getColumns().clear();
        readingTable.getColumns().addAll(id, player1, player2);

        id.setCellValueFactory(new PropertyValueFactory<TennisMatchDTO, String>("id"));
        player2.setCellValueFactory(new PropertyValueFactory<TennisMatchDTO, String>("player1"));
        player1.setCellValueFactory(new PropertyValueFactory<TennisMatchDTO, String>("player2"));

        ObservableList<TennisMatchDTO> data = tennisService.requestMatches();
        readingTable.setItems(data);
    }

    public void viewSets(ActionEvent actionEvent) {
        TableColumn setID = new TableColumn("Set ID");

        ArrayList<String> setNum = new ArrayList<String>();
        readingTable.getColumns().clear();
        readingTable.getColumns().addAll(setID);

        setID.setCellValueFactory(new PropertyValueFactory<TennisSetDTO, String>("id"));
        int id = Integer.parseInt(matchID.getText().toString());

        ObservableList<TennisSetDTO> data = tennisService.requestSets(id);

        readingTable.setItems(data);
    }

    public void startMatch(ActionEvent actionEvent) {
    }
}
