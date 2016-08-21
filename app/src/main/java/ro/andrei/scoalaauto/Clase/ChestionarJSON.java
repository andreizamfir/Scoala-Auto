package ro.andrei.scoalaauto.Clase;

/**
 * Created by Andrei on 03-Dec-15.
 */
public class ChestionarJSON {
    private String nrIntrebare;
    private String intrebare;
    private String raspunsA;
    private String raspunsB;
    private String raspunsC;
    private Boolean variantaA;
    private Boolean variantaB;
    private Boolean variantaC;

    public String getNrIntrebare() {
        return nrIntrebare;
    }

    public void setNrIntrebare(String nrIntrebare) {
        this.nrIntrebare = nrIntrebare;
    }

    public String getIntrebare() {
        return intrebare;
    }

    public String getRaspunsA() {
        return raspunsA;
    }

    public String getRaspunsB() {
        return raspunsB;
    }

    public String getRaspunsC() {
        return raspunsC;
    }

    public Boolean getVariantaA() {
        return variantaA;
    }

    public Boolean getVariantaB() {
        return variantaB;
    }

    public Boolean getVariantaC() {
        return variantaC;
    }

    public void setIntrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    public void setVariantaC(Boolean variantaC) {
        this.variantaC = variantaC;
    }

    public void setVariantaB(Boolean variantaB) {
        this.variantaB = variantaB;
    }

    public void setVariantaA(Boolean variantaA) {
        this.variantaA = variantaA;
    }

    public void setRaspunsC(String raspunsC) {
        this.raspunsC = raspunsC;
    }

    public void setRaspunsA(String raspunsA) {
        this.raspunsA = raspunsA;
    }

    public void setRaspunsB(String raspunsB) {
        this.raspunsB = raspunsB;
    }
}

