package layer_business;

import dto.TennisMatchDTO;
import dto.TennisSetDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import layer_data_access.repo.UserRepo;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TennisService {

    public TennisMatch createNewMatch(User player1, User player2) {
        TennisSet tennisSet = new TennisSet();
        tennisSet.setGames(Collections.singletonList(getEmptyTennisGame()));

        List<TennisSet> sets = new ArrayList<>();
        sets.add(tennisSet);

        TennisMatch tennisMatch = new TennisMatch();
        tennisMatch.setPlayer1(player1);
        tennisMatch.setPlayer2(player2);

        tennisMatch.setSets(sets);
        return tennisMatch;
    }



    private TennisGame getEmptyTennisGame() {
        TennisGame tennisGame = new TennisGame();
        tennisGame.setP1Score("0");
        tennisGame.setP2Score("0");
        return tennisGame;
    }

    public ObservableList<TennisMatchDTO> requestMatches(){
        List<TennisMatch> matches = UserRepo.readMatches();
        ArrayList<TennisMatchDTO> matchDTOS = new ArrayList<TennisMatchDTO>();
        for(TennisMatch m : matches) {
            matchDTOS.add(new TennisMatchDTO(m));
        }

        return  FXCollections.observableArrayList(matchDTOS);
    }

    public ObservableList<TennisSetDTO> requestSets(int id){
        List<TennisSet> sets = UserRepo.readSets(id);
        ArrayList<TennisSetDTO> setDTOS = new ArrayList<TennisSetDTO>();
        for(TennisSet s : sets){
            setDTOS.add(new TennisSetDTO(s));
        }

        return FXCollections.observableArrayList(setDTOS);
    }

}
