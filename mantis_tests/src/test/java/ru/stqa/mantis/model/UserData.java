package ru.stqa.mantis.model;

public record UserData(String username, String email) {

    public UserData() {
        this("", "");
    }

    public UserData withUserName(String username) {
        return new UserData(username, this.email);
    }

    public UserData withEmail(String email) {
        return new UserData(this.username, email);
    }

}