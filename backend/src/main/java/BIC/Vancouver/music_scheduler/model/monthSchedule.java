package BIC.Vancouver.music_scheduler.model;

import java.util.Date;
import java.util.List;

public class monthSchedule {
    private List<String> dates;
    private List<user> worshipLeaders;
    private List<List<user>> singers;
    private List<user> keyboardUsers;
    private List<user> fillerUsers;
    private List<user> drumUsers;
    private List<user> bassUsers;
    private List<user> guitarUsers;
    private List<user> multimediaUsers;
    private List<user> soundSystemUsers;

    public List<String> getDates() {
        return dates;
    }

    public List<user> getWorshipLeaders() {
        return worshipLeaders;
    }

    public List<List<user>> getSingers() {
        return singers;
    }

    public List<user> getKeyboardUsers() {
        return keyboardUsers;
    }

    public List<user> getFillerUsers() {
        return fillerUsers;
    }

    public List<user> getDrumUsers() {
        return drumUsers;
    }

    public List<user> getBassUsers() {
        return bassUsers;
    }

    public List<user> getGuitarUsers() {
        return guitarUsers;
    }

    public List<user> getMultimediaUsers() {
        return multimediaUsers;
    }

    public List<user> getSoundSystemUsers() {
        return soundSystemUsers;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public void setWorshipLeaders(List<user> worshipLeaders) {
        this.worshipLeaders = worshipLeaders;
    }

    public void setSingers(List<List<user>> singers) {
        this.singers = singers;
    }

    public void setKeyboardUsers(List<user> keyboardUsers) {
        this.keyboardUsers = keyboardUsers;
    }

    public void setFillerUsers(List<user> fillerUsers) {
        this.fillerUsers = fillerUsers;
    }

    public void setDrumUsers(List<user> drumUsers) {
        this.drumUsers = drumUsers;
    }

    public void setBassUsers(List<user> bassUsers) {
        this.bassUsers = bassUsers;
    }

    public void setGuitarUsers(List<user> guitarUsers) {
        this.guitarUsers = guitarUsers;
    }

    public void setMultimediaUsers(List<user> multimediaUsers) {
        this.multimediaUsers = multimediaUsers;
    }

    public void setSoundSystemUsers(List<user> soundSystemUsers) {
        this.soundSystemUsers = soundSystemUsers;
    }
}
