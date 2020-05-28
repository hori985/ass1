package layer_business;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import dto.TennisGameDTO;
import dto.TennisMatchDTO;
import dto.TennisSetDTO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

public class MatchReportPDF implements MatchReport {
    TennisMatchDTO match;

    public MatchReportPDF(TennisMatchDTO match){
        this.match = match;
    }

    @Override
    public void generate() throws FileNotFoundException, DocumentException {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("match_" + match.getId() + ".pdf"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            Paragraph report = new Paragraph("Match " + match.getId() + ": " + match.getPlayer1() + " vs " + match.getPlayer2(), font);

            for(int i = 0; i < match.getSets().size(); i++) {
                TennisSetDTO currentSet = match.getSets().get(i);
                report.add("\nSet #"  + (i+1) + ": \n");

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
                    report.add("Game #" + (player1Score + player2Score) + ":" + currentGame.getP1Score() + " - " + currentGame.getP2Score() + "\n");
                }

                report.add(player1Score + " - " + player2Score + "\n");
            }
            document.add(report);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
