package model;

public record ContactData(String id, String firstName, String lastName, String photo, String home, String mobile,
                          String work, String phone2) {

    public ContactData() {
        this("", "","","", "", "", "", "");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.photo, "", "", "", "");
    }
    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName,  this.photo, "", "", "", "");
    }
    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.photo, "", "", "", "");
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, photo, "", "", "", "");
    }
    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, home, this.mobile, this.work, this.phone2);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, mobile, this.work, this.phone2);
    }
    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, this.mobile, work, this.phone2);
    }
    public ContactData withPhone2(String phone2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, this.mobile, this.work, phone2);
    }
}
