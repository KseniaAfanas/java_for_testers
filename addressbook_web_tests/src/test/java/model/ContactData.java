package model;

public record ContactData(String firstname, String middlename, String lastname, String nickname, String mobile, String email) {//record-класс для моделирования данных
    public ContactData() {
        this ("","","","","","");
    }
    public ContactData WithFirstname(String firstname) {
        return new ContactData(firstname, this.middlename, this.lastname, this.nickname, this.mobile,this.email);
    }
    public ContactData WithMiddlename(String middlename) {
        return new ContactData(this.firstname, middlename, this.lastname, this.nickname, this.mobile,this.email);
    }
    public ContactData WithLastname(String lastname) {
        return new ContactData(this.firstname, this.middlename, lastname, this.nickname, this.mobile,this.email);
    }
    public ContactData WithNickname(String nickname) {
        return new ContactData(this.firstname, this.middlename, this.lastname, nickname, this.mobile,this.email);
    }
    public ContactData WithMobile(String mobile) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.nickname, mobile,this.email);
    }
    public ContactData WithEmail(String email) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.nickname, this.mobile, email);
    }

}