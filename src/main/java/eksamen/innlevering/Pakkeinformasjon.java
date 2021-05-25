package eksamen.innlevering;

public class Pakkeinformasjon {
    private String fornavn, etternavn, adresse, postnr, telefonnr, epost;
    private double volum, vekt;

    public Pakkeinformasjon(String fornavn, String etternavn, String adresse, String postnr, String telefonnr, String epost, double volum, double vekt) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse =adresse;
        this.postnr = postnr;
        this.telefonnr = telefonnr;
        this.epost = epost;
        this.volum = volum;
        this.vekt = vekt;
    }
    public Pakkeinformasjon(){}

    public double getVekt() {
        return vekt;
    }

    public void setVekt(double vekt) {
        this.vekt = vekt;
    }

    public double getVolum() {
        return volum;
    }

    public void setVolum(double volum) {
        this.volum = volum;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }
}
