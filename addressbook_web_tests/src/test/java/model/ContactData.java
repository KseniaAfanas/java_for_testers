package model;

public record ContactData(String firstname, String middlename, String lastname, String nickname, String mobile,String email) {
    public ContactData() {
        this ("","","","","","");
    }

    public ContactData WithFirstname(String firstname) {
        return new GroupData(firstname, this.middlename, this.lastname, this.nickname, this.mobile,this.email);
    }
    public ContactData WithMiddlename(String middlename) {
        return new GroupData(this.firstname, middlename, this.lastname, this.nickname, this.mobile,this.email);
    }
    public ContactData WithLastname(String lastname) {
        return new GroupData(this.firstname, this.middlename, lastname, this.nickname, this.mobile,this.email);
    }
    public ContactData WithNickname(String nickname) {
        return new GroupData(this.firstname, this.middlename, this.lastname, nickname, this.mobile,this.email);
    }
    public ContactData WithMobile(String mobile) {
        return new GroupData(this.firstname, this.middlename, this.lastname, this.nickname, mobile,this.email);
    }
    public ContactData WithEmail(String email) {
        return new GroupData(this.firstname, this.middlename, this.lastname, this.nickname, this.mobile, email);
    }

}