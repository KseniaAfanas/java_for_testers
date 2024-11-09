package ru.stqa.addressbook.model;

public record ContactData(String id,
                          String middlename,
                          String lastname,
                          String nickname,
                          String address,
                          String email,
                          String email2,
                          String email3,
                          String home,
                          String mobile,
                          String work,
                          String secondary,
                          String firstname,
                          String foto
) {//record-класс для моделирования данных
    public ContactData() {
        this ("", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }
    public ContactData WithId(String id)
    {return new ContactData(id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2 ,this.email3, this.home, this.mobile, this.work, this.secondary, firstname, this.foto);
    }
    public ContactData WithFirstname(String firstname) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2 ,this.email3, this.home, this.mobile, this.work, this.secondary, firstname, this.foto);
    }
    public ContactData WithMiddlename(String middlename) {
        return new ContactData(this.id, middlename, this.lastname, this.nickname, this.address, this.email,this.email2 ,this.email3, this.home, this.mobile, this.work, this.secondary, this.firstname, this.foto);
    }
    public ContactData WithLastname(String lastname) {
        return new ContactData(this.id, this.middlename, lastname, this.nickname, this.address, this.email,this.email2 ,this.email3, this.home, this.mobile, this.work, this.secondary, this.firstname, this.foto);
    }
    public ContactData WithAddress(String address) {
        return new ContactData(this.id, this.middlename, lastname, this.nickname, address, this.email,this.email2 ,this.email3, this.home, this.mobile, this.work, this.secondary, this.firstname, this.foto);
    }
    public ContactData WithNickname(String nickname) {
        return new ContactData(this.id, this.middlename, this.lastname, nickname, this.address, this.email,this.email2 ,this.email3, this.home, this.mobile, this.work, this.secondary, this.firstname, this.foto);
    }
        public ContactData WithEmail(String email) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, email,this.email2 ,this.email3, this.home, this.mobile, this.work, this.secondary, this.firstname, this.foto);
    }
    public ContactData WithEmail2(String email2) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,email2 ,this.email3, this.home, this.mobile, this.work, this.secondary, this.firstname, this.foto);
    }
    public ContactData WithEmail3(String email3) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2 ,email3, this.home, this.mobile, this.work, this.secondary, this.firstname, this.foto);
    }
    public ContactData WithFoto(String foto) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2 ,this.email3, this.home, this.mobile, this.work, this.secondary, this.firstname, foto);
    }
    public ContactData WithHome(String home) {//домашний телефон
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2 ,this.email3, home, this.mobile, this.work, this.secondary, this.firstname, foto);
    }
    public ContactData WithMobile(String mobile) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2 ,this.email3, this.home, mobile, this.work, this.secondary, this.firstname, this.foto);
    }
    public ContactData WithWork(String work) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2 ,this.email3, this.home, this.mobile, work, this.secondary, this.firstname, foto);
    }
    public ContactData WithSecondary(String secondary) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2 ,this.email3, this.home, this.mobile, this.work, secondary, this.firstname, foto);
    }
}