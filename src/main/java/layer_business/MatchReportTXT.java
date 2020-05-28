package layer_business;

import com.itextpdf.text.DocumentException;
import dto.TennisGameDTO;
import dto.TennisMatchDTO;
import dto.TennisSetDTO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class MatchReportTXT implements MatchReport {
    TennisMatchDTO match;

    public MatchReportTXT(TennisMatchDTO match){
        this.match = match;
    }

    @Override
    public void generate() throws FileNotFoundException, DocumentException {
        StringBuilder file = new StringBuilder();
        file.append("Match " + match.getId() + ": " + match.getPlayer1() + " vs " + match.getPlayer2());
        for(int i = 0; i < match.getSets().size(); i++) {
            file.append("\nSet #"  + i + ": ");
            TennisSetDTO currentSet = match.getSets().get(i);

            int player1Score=0, player2Score=0;

            for(int j = 0; j < currentSet.getGames().size(); j++){
                TennisGameDTO currentGame = currentSet.getGames().get(j);
                if(Integer.parseInt(currentGame.getP1Score()) > Integer.parseInt(currentGame.getP2Score())){
                    player1Score++;
                }
                else{
                    if(Integer.parseInt(currentGame.getP1Score()) < Integer.parseInt(currentGame.getP2Score())){
                        player2Score++;
                    }
                }

            file.append(player1Score + " - " + player2Score);
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./match_" +match.getId() + "#" + Math.random()%10000  +".txt"));
            writer.write(file.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
