package ru.stqa.addressbook.model;

public record ContactData(String id, String middlename, String lastname, String nickname, String mobile, String email,
                          String firstname, String foto) {//record-класс для моделирования данных
    public ContactData() {
        this ("", "", "", "", "", "", "", "");
    }
    public ContactData WithId(String id) {return new ContactData(id, this.middlename, this.lastname, this.nickname, this.mobile, this.email, firstname,this.foto);}//метод,

    public ContactData WithFirstname(String firstname) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.mobile, this.email, firstname,this.foto);
    }
    public ContactData WithMiddlename(String middlename) {
        return new ContactData(this.id, middlename, this.lastname, this.nickname, this.mobile, this.email, this.firstname,this.foto);
    }
    public ContactData WithLastname(String lastname) {
        return new ContactData(this.id, this.middlename, lastname, this.nickname, this.mobile, this.email, this.firstname,this.foto);
    }
    public ContactData WithNickname(String nickname) {
        return new ContactData(this.id, this.middlename, this.lastname, nickname, this.mobile, this.email, this.firstname,this.foto);
    }
    public ContactData WithMobile(String mobile) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, mobile, this.email, this.firstname,this.foto);
    }
    public ContactData WithEmail(String email) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.mobile, email, this.firstname,this.foto);
    }
    public ContactData WithFoto(String foto) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.mobile, this.email, this.firstname,foto);
    }

}