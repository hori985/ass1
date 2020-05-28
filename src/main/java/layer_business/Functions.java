package layer_business;

import dto.AdminDTO;
import dto.UserDTO;
import dto.TennisMatchDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import layer_data_access.repo.MatchRepo;
import layer_data_access.repo.UserRepo;
import layer_data_access.repo.AdminRepo;
import layer_presentation.util.AlertBox;
import model.Admin;
import model.TennisMatch;
import model.TennisSet;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {
    public UserDTO loginPlayer(String mail, String password) {

        UserDTO user = new UserDTO(UserRepo.findUserByMail(mail));
        //do operations on user
        if(user == null) return  null;
        if (!password.equals(user.getPassword())) return null;

        return user;
    }

    public AdminDTO loginAdmin(String mail, String password) {

        AdminDTO admin = new AdminDTO(AdminRepo.findAdminByMail(mail));
        //do operations on admin
        if(admin == null) return null;
        if(!password.equals(admin.getPassword())) return null;


        return admin;
    }

    public ObservableList<UserDTO> requestUsers() {
        ArrayList<UserDTO> usersList = new ArrayList<UserDTO>();
        List<User> users = AdminRepo.readUsers();

        for(User u : users){
            usersList.add(new UserDTO(u));
        }

        ObservableList<UserDTO> userDTOS = FXCollections.observableArrayList(usersList);
        return  userDTOS;
    }


    public void requestCreateUser(String mail, String password, String name) {
        if(UserRepo.findUserByMail(mail) == null){
            User newUser = new User(0, mail, password, name);
            AdminRepo.createNewUser(newUser);
        }
        else {
            System.err.println("User with that mail already exists!");
        }
    }

    public void requestUpdateUser(int id, String mail, String password, String name){
        User user = UserRepo.findUserByID(id);
        if(!name.equals(""))  user.setName(name);
        if(!password.equals(""))  user.setPassword(password);
        if(!mail.equals("")) user.setMail(mail);

        AdminRepo.updateUser(user);
    }

    public void requestDeleteUser(int id){
        AdminRepo.deleteUser(id);
    }

    public ObservableList<TennisMatchDTO> requestMatches(){
        ArrayList<TennisMatchDTO> matchesList = new ArrayList<TennisMatchDTO>();
        List<TennisMatch> matches = AdminRepo.readMatches();

        for(TennisMatch t : matches){
            matchesList.add(new TennisMatchDTO(t));
        }

        ObservableList<TennisMatchDTO> matchesDTOS = FXCollections.observableArrayList(matchesList);
        return  matchesDTOS;
    }

    public void requestCreateMatch(int player1ID, int player2ID){
        User player1 = UserRepo.findUserByID(player1ID);
        User player2 = UserRepo.findUserByID(player2ID);

        TennisMatch match = new TennisMatch(0, player1, player2, new ArrayList<TennisSet>());
        AdminRepo.createTennisMatch(match);
    }

    public int requestUpdateMatch(int matchID, String player1ID, String player2ID){
        TennisMatch match = MatchRepo.findMatchByID(matchID);
        if(!player1ID.equals("")){
            match.getPlayer1().setId(Integer.parseInt(player1ID));
        }
        if(!player2ID.equals("")){
            match.getPlayer2().setId(Integer.parseInt(player2ID));
        }

        if(match.getPlayer1().getId() == match.getPlayer2().getId()){ return -1;
        }
        else{AdminRepo.updateTennisMatch(match); return 1;}
    }

    public void requestDeleteMatch(int matchID){
        AdminRepo.deleteTennisMatch(matchID);
    }
}
