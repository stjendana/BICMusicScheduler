package BIC.Vancouver.music_scheduler.model;

import java.util.Date;
import java.util.List;

public class monthSchedule {
    private List<String> dates;
    private List<List<user>> worshipLeaders;
    private List<List<user>> singers;
    private List<List<user>> keyboardUsers;
    private List<List<user>> fillerUsers;
    private List<List<user>> drumUsers;
    private List<List<user>> bassUsers;
    private List<List<user>> guitarUsers;
    private List<List<user>> multimediaUsers;
    private List<List<user>> soundSystemUsers;

    public List<String> getDates() {
        return dates;
    }

    public List<List<user>> getWorshipLeaders() {
        return worshipLeaders;
    }

    public List<List<user>> getSingers() {
        return singers;
    }

    public List<List<user>> getKeyboardUsers() {
        return keyboardUsers;
    }

    public List<List<user>> getFillerUsers() {
        return fillerUsers;
    }

    public List<List<user>> getDrumUsers() {
        return drumUsers;
    }

    public List<List<user>> getBassUsers() {
        return bassUsers;
    }

    public List<List<user>> getGuitarUsers() {
        return guitarUsers;
    }

    public List<List<user>> getMultimediaUsers() {
        return multimediaUsers;
    }

    public List<List<user>> getSoundSystemUsers() {
        return soundSystemUsers;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public void setWorshipLeaders(List<List<user>> worshipLeaders) {
        this.worshipLeaders = worshipLeaders;
    }

    public void setSingers(List<List<user>> singers) {
        this.singers = singers;
    }

    public void setKeyboardUsers(List<List<user>> keyboardUsers) {
        this.keyboardUsers = keyboardUsers;
    }

    public void setFillerUsers(List<List<user>> fillerUsers) {
        this.fillerUsers = fillerUsers;
    }

    public void setDrumUsers(List<List<user>> drumUsers) {
        this.drumUsers = drumUsers;
    }

    public void setBassUsers(List<List<user>> bassUsers) {
        this.bassUsers = bassUsers;
    }

    public void setGuitarUsers(List<List<user>> guitarUsers) {
        this.guitarUsers = guitarUsers;
    }

    public void setMultimediaUsers(List<List<user>> multimediaUsers) {
        this.multimediaUsers = multimediaUsers;
    }

    public void setSoundSystemUsers(List<List<user>> soundSystemUsers) {
        this.soundSystemUsers = soundSystemUsers;
    }
}
