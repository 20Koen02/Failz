package nl.koen02.failz.ui.shared;

import java.io.Serializable;

public class ListItemData implements Serializable {
    private String code;
    private Vaksoort vaksoort;
    private Float cijfer;
    private Integer ec;
    private String id;
    private String note;

    public ListItemData(String code, Vaksoort vaksoort, Float cijfer, Integer ec, String note, String id) {
        this.code = code;
        this.vaksoort = vaksoort;
        this.cijfer = cijfer;
        this.ec = ec;
        this.id = id;
        this.note = note;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getCijfer() {
        return cijfer;
    }

    public void setCijfer(Float cijfer) {
        this.cijfer = cijfer;
    }

    public Vaksoort getVaksoort() {
        return vaksoort;
    }

    public void setVaksoort(Vaksoort vaksoort) {
        this.vaksoort = vaksoort;
    }

    public Integer getEc() {
        return ec;
    }

    public void setEc(Integer ec) {
        this.ec = ec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
