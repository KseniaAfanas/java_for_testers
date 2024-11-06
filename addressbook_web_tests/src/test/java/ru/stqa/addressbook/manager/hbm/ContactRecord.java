package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.stqa.addressbook.common.CommonFunctions;

import java.util.Date;

@Entity
@Table(name="addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String nickname;
    public String firstname;

    public String middlename;

    public String lastname;

    public String mobile;

    public String email;

    public String photo;
    public String company = new String ();//обязательное поле
    public String title=new String ();//обязательное поле
    public String address=new String ();//обязательное поле
    public String home=new String ();//обязательное поле
    public String work=new String ();//обязательное поле
    public String fax=new String ();//обязательное поле
    public String email2=new String ();//обязательное поле
    public String email3=new String ();//обязательное поле
    public String homepage =new String ();//обязательное поле

    public ContactRecord(){//конструктор который принимает нулевой набор параметров
    }
    public ContactRecord(int id, String middlename, String lastname, String nickname, String mobile, String email, String firstname, String photo){//конструктор который принимает все параметры
        this.id = id;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.firstname = firstname;
        this.photo = photo;
    }
}


