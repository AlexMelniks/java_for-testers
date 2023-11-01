package model;

public record AddData(String id, String firstName, String lastName) {

    public AddData() {
        this("", "", "");
    }
    public AddData withId(String id) {
        return new AddData(id, this.firstName, this.lastName);
    }
    public AddData withFirstName(String firstName) {
        return new AddData(this.id, firstName, this.lastName);
    }
    public AddData withLastName(String lastName) {
        return new AddData(this.id, this.firstName, lastName);
    }
}
