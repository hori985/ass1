package layer_business;

import com.itextpdf.text.DocumentException;
import dto.TennisMatchDTO;

import java.io.FileNotFoundException;

public interface MatchReport {
    public void generate() throws FileNotFoundException, DocumentException;
}
