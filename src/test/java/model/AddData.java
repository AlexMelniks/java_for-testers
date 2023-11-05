package model;

public record AddData(String id, String firstName, String lastName, String photo) {

    public AddData() {
        this("", "", "","");
    }
    public AddData withId(String id) {
        return new AddData(id, this.firstName, this.lastName, this.photo);
    }
    public AddData withFirstName(String firstName) {
        return new AddData(this.id, firstName, this.lastName, this.photo);
    }
    public AddData withLastName(String lastName) {
        return new AddData(this.id, this.firstName, lastName, this.photo);
    }
    public AddData withPhoto(String photo) {
        return new AddData(this.id, this.firstName, this.lastName, photo);
    }
}
