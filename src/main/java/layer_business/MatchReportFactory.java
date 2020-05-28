package layer_business;

import dto.TennisMatchDTO;

public class MatchReportFactory {
    public MatchReportFactory(){ }

    public MatchReport create(TennisMatchDTO matchDTO, String format){
        if(format.equals("pdf")) return new MatchReportPDF(matchDTO);
        if(format.equals("txt")) return new MatchReportTXT(matchDTO);
        return null;
    }
}
