package model;

public record AddData(String id, String firstName, String middleName, String lastName) {

    public AddData() {
        this("", "", "", "");
    }
    public AddData withId(String id) {
        return new AddData(id, this.firstName, this.middleName, this.lastName);
    }
    public AddData withFirstName(String firstName) {
        return new AddData(this.id, firstName, this.middleName, this.lastName);
    }
    public AddData withMiddleName(String middleName) {
        return new AddData(this.id, this.firstName, middleName, this.lastName);
    }
    public AddData withLastName(String lastName) {
        return new AddData(this.id, this.firstName, this.middleName, lastName);
    }
}
