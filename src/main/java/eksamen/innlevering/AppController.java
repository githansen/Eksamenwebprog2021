package eksamen.innlevering;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.PreparedStatement;

@RestController
public class AppController {
    @Autowired
    private JdbcTemplate db;
    private Logger logger = LoggerFactory.getLogger(AppController.class);

    @GetMapping("/Sjekkpostnr")
    public boolean sjekkpostnr(String postnr){
        String sql = "SELECT COUNT(*) FROM poststed WHERE postnr=?";
        int antall = db.queryForObject(sql, Integer.class, postnr);
        if(antall > 0){
            return true;
        }
        else{
            return false;
        }
    }

    @PostMapping("/Lagresending")
    @Transactional
    public void lagre(Pakkeinformasjon pakke, HttpServletResponse response )throws IOException {
        String sql1 = "INSERT INTO kunde (fornavn, etternavn, adresse, postnr, telefonnr, epost) VALUES (?,?,?,?,?,?)";
        String sql2 = "INSERT INTO pakke (kid, volum, vekt) VALUES(?,?,?)";
        String regexpnavn = "[a-zA-ZæøåÆØÅ. \\-]{3,15}";
        String regexppost = "[0-9]{4}";
        boolean fornavnok = pakke.getFornavn().matches(regexpnavn);
        boolean etternavnok = pakke.getEtternavn().matches(regexpnavn);
        boolean postnrok = pakke.getPostnr().matches(regexppost);
        KeyHolder id = new GeneratedKeyHolder();
        if(fornavnok && etternavnok &&postnrok) {
            try {
                db.update(con -> {
                    PreparedStatement par = con.prepareStatement(sql1, new String[]{"kid"});
                    par.setString(1, pakke.getFornavn());
                    par.setString(2, pakke.getEtternavn());
                    par.setString(3, pakke.getAdresse());
                    par.setString(4, pakke.getPostnr());
                    par.setString(5, pakke.getTelefonnr());
                    par.setString(6, pakke.getEpost());
                    return par;
                }, id);
                int kid = id.getKey().intValue();
                db.update(sql2, kid, pakke.getVolum(), pakke.getVekt());
            } catch (Exception e) {
                logger.error("En feil skjedde i lagringen");
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i lagringen");
            }
        }
        else{
            logger.error("Feil i inputvalidering");
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i inputvalidering");
        }
    }


}
