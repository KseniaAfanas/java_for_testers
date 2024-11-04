package ru.stqa.addressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase{
    private SessionFactory sessionFactory;
    public HibernateHelper (ApplicationManager manager){//получаем доступ к менеджеру
        super(manager);
        sessionFactory = new Configuration() //создем фабрику сессий
                        //.addAnnotatedClass(Book.class)
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }
    static List <GroupData> convertList (List<GroupRecord> records){//функция, которая преобразовывает список объектов одного типа в список объектов друго типа
        List<GroupData> result = new ArrayList<>();
        for (var record:records){
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    static List <ContactData> convertList1 (List<ContactRecord> records){//функция, которая преобразовывает список объектов одного типа в список объектов друго типа
        List<ContactData> result = new ArrayList<>();
        for (var record:records){
            result.add(convert(record));
        }
        return result;
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData(""+record.id, record.middlename, record.lastname, record.nickname, record.mobile, record.email, record.firstname,"");
    }

public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {//функция обратится к БД и вернет результат.Функция выполняется в контексте сессии, которая автоматически открывается и закрывается
return session.createQuery("from GroupRecord", GroupRecord.class).list();//внутренняя функция
        }));
}
    public List<ContactData> getContactList() {
        return convertList1(sessionFactory.fromSession(session -> {//функция обратится к БД и вернет результат.Функция выполняется в контексте сессии, которая автоматически открывается и закрывается
            return session.createQuery("from ContactRecord", ContactRecord.class).list();//внутренняя функция
        }));
    }
}
