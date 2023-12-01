package model;

public record ContactData(String id, String firstName, String lastName, String photo, String home, String mobile,
                          String work, String phone2, String email, String email2, String email3, String address,
                          String address2) {

    public ContactData() {
        this("", "","","", "", "", "", "", "", "", "", "", "");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.photo, this.home, this.mobile, this.work, this.phone2, this.email, this.email2, this.email3, this.address, this.address);
    }
    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName,  this.photo, this.home, this.mobile, this.work, this.phone2, this.email, this.email2, this.email3, this.address, this.address);
    }
    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.photo, this.home, this.mobile, this.work, this.phone2, this.email, this.email2, this.email3, this.address, this.address);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, photo, this.home, this.mobile, this.work, this.phone2, this.email, this.email2, this.email3, this.address, this.address);
    }
    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, home, this.mobile, this.work, this.phone2, this.email, this.email2, this.email3, this.address, this.address);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, mobile, this.work, this.phone2, this.email, this.email2, this.email3, this.address, this.address);
    }
    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, this.mobile, work, this.phone2, this.email, this.email2, this.email3, this.address, this.address);
    }
    public ContactData withPhone2(String phone2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, this.mobile, this.work, phone2, this.email, this.email2, this.email3, this.address, this.address);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, this.mobile, this.work, this.phone2, email, this.email2, this.email3, this.address, this.address);
    }
    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, this.mobile, this.work, this.phone2, this.email, email2, this.email3, this.address, this.address);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, this.mobile, this.work, this.phone2, this.email, this.email2, email3, this.address, this.address);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, this.mobile, this.work, this.phone2, this.email, this.email2, email3, address, this.address);
    }
    public ContactData withAddress2(String address2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.home, this.mobile, this.work, this.phone2, this.email, this.email2, email3, this.address, address2);
    }
}
