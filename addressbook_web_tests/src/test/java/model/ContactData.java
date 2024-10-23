package model;

public record ContactData(String id, String middlename, String lastname, String nickname, String mobile, String email,
                          String firstname) {//record-класс для моделирования данных
    public ContactData() {
        this ("", "", "", "", "", "", "");
    }
    public ContactData WithFirstname(String firstname) {
        return new ContactData("", this.middlename, this.lastname, this.nickname, this.mobile, this.email, firstname);
    }
    public ContactData WithMiddlename(String middlename) {
        return new ContactData("", middlename, this.lastname, this.nickname, this.mobile, this.email, this.firstname);
    }
    public ContactData WithLastname(String lastname) {
        return new ContactData("", this.middlename, lastname, this.nickname, this.mobile, this.email, this.firstname);
    }
    public ContactData WithNickname(String nickname) {
        return new ContactData("", this.middlename, this.lastname, nickname, this.mobile, this.email, this.firstname);
    }
    public ContactData WithMobile(String mobile) {
        return new ContactData("", this.middlename, this.lastname, this.nickname, mobile, this.email, this.firstname);
    }
    public ContactData WithEmail(String email) {
        return new ContactData("", this.middlename, this.lastname, this.nickname, this.mobile, email, this.firstname);
    }

}