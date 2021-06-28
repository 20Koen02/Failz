package nl.koen02.failz.data.model;

import nl.koen02.failz.ui.shared.Vaksoort;

public class Subject {
    private String code;
    private int ec;
    private float score;
    private Vaksoort type;
    private String userId;
    private String note;

    public Subject() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getEc() {
        return ec;
    }

    public void setEc(int ec) {
        this.ec = ec;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Vaksoort getType() {
        return type;
    }

    public void setType(Vaksoort type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
