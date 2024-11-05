package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.stqa.addressbook.common.CommonFunctions;
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
}


