package nl.koen02.failz.ui.home;

import java.io.Serializable;

public class ListItemData implements Serializable {
    private String code;
    private Vaksoort vaksoort;
    private float cijfer;
    private int ec;

    public ListItemData(String code, Vaksoort vaksoort, float cijfer, int ec) {
        this.code = code;
        this.vaksoort = vaksoort;
        this.cijfer = cijfer;
        this.ec = ec;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getCijfer() {
        return cijfer;
    }

    public void setCijfer(float cijfer) {
        this.cijfer = cijfer;
    }

    public Vaksoort getVaksoort() {
        return vaksoort;
    }

    public void setVaksoort(Vaksoort vaksoort) {
        this.vaksoort = vaksoort;
    }

    public int getEc() {
        return ec;
    }

    public void setEc(int ec) {
        this.ec = ec;
    }
}
