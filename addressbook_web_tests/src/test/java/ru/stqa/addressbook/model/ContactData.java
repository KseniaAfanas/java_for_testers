package ru.stqa.addressbook.model;

public record ContactData(String id,
                          String middlename,
                          String lastname,
                          String nickname,
                          String address,
                          String email,
                          String firstname,
                          String foto,
                          String home,
                          String mobile,
                          String work,
                          String secondary) {//record-класс для моделирования данных
    public ContactData() {
        this ("", "", "", "", "", "", "", "", "", "", "", "");
    }
    public ContactData WithId(String id)
    {return new ContactData(id, this.middlename, this.lastname, this.nickname, this.address, this.email, firstname, this.foto, this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData WithFirstname(String firstname) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email, firstname, this.foto, this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData WithMiddlename(String middlename) {
        return new ContactData(this.id, middlename, this.lastname, this.nickname, this.address, this.email, this.firstname, this.foto, this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData WithLastname(String lastname) {
        return new ContactData(this.id, this.middlename, lastname, this.nickname, this.address, this.email, this.firstname, this.foto, this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData WithAddress(String address) {
        return new ContactData(this.id, this.middlename, lastname, this.nickname, address, this.email, this.firstname, this.foto, this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData WithNickname(String nickname) {
        return new ContactData(this.id, this.middlename, this.lastname, nickname, this.address, this.email, this.firstname, this.foto, this.home, this.mobile, this.work, this.secondary);
    }
        public ContactData WithEmail(String email) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, email, this.firstname, this.foto, this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData WithFoto(String foto) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email, this.firstname, foto, this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData WithHome(String home) {//домашний телефон
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email, this.firstname, foto, home, this.mobile, this.work, this.secondary);
    }
    public ContactData WithMobile(String mobile) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email, this.firstname, this.foto, this.home, mobile, this.work, this.secondary);
    }
    public ContactData WithWork(String work) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email, this.firstname, foto, this.home, this.mobile, work, this.secondary);
    }
    public ContactData WithSecondary(String secondary) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email, this.firstname, foto, this.home, this.mobile, this.work, secondary);
    }
}