package ru.stqa.addressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase{
    private SessionFactory sessionFactory;
    public HibernateHelper (ApplicationManager manager){//получаем доступ к менеджеру
        super(manager);
        sessionFactory = new Configuration() //создем фабрику сессий
                        //.addAnnotatedClass(Book.class)
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")//добавили zeroDateTimeBehavior=convertToNull чтобы нулевые даты игнорировались
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }
    static List <GroupData> convertList (List<GroupRecord> records){//функция, которая преобразовывает список объектов одного типа в список объектов друго типа
return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());//делаем из списка поток, потом применяем к потоку трансформатор, потом результат собирает в список и возвращает обратно
    }
    /*
        static List <GroupData> convertList (List<GroupRecord> records){//функция, которая преобразовывает список объектов одного типа в список объектов друго типа
        List<GroupData> result = new ArrayList<>();
        for (var record:records){
            result.add(convert(record));
        }
        return result;
    }
     */

    private static GroupData convert(GroupRecord record) {
                return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id=data.id();//чтобы не ломался parseInt если он пуст, то присваиваем 0
        if ("".equals(id)){
            id="0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());//преобразовываем идентификатор из строки в число
    }

    static List <ContactData> convertContactList (List<ContactRecord> records){//функция, которая преобразовывает список объектов одного типа в список объектов друго типа
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());//делаем из списка поток, потом применяем к потоку трансформатор, потом результат собирает в список и возвращает обратно
    }
    /*
        static List <ContactData> convertContactList (List<ContactRecord> records){//функция, которая преобразовывает список объектов одного типа в список объектов друго типа
        List<ContactData> result = new ArrayList<>();
        for (var record:records){
            result.add(convert(record));
        }
        return result;
    }
     */

    //ТУТ МЕНЯТЬ ВСЁ!!!
    private static ContactData convert(ContactRecord record) {//сделали в Лекция 7.5.
        return new ContactData()
                .WithId("" + record.id)
                .WithMiddlename(record.middlename)
                .WithLastname(record.lastname)
                .WithNickname(record.nickname)
                .WithAddress(record.address)
                .WithEmail(record.email)
                .WithEmail2(record.email2)
                .WithEmail3(record.email3)
                .WithFirstname(record.firstname)
                .WithFoto(record.photo)
                .WithHome(record.home)
                .WithMobile(record.mobile)
                .WithWork(record.work)
                .WithSecondary(record.phone2);
    }

    /*
        private static ContactData convert(ContactRecord record) {//было так ДО Лекция 7.5. Метод обратных (обходных) проверок
        return new ContactData(""+record.id, record.middlename, record.lastname, record.nickname, "", record.email, record.firstname, record.photo, "", record.mobile, "", "");
    }
     */

    private static ContactRecord convert(ContactData data) {
        var id=data.id();//чтобы не ломался parseInt если он пуст, то присваиваем 0
        if ("".equals(id)){
            id="0";
        }
        return new ContactRecord(Integer.parseInt(id), data.middlename(), data.lastname(), data.nickname(), data.mobile(), data.email(), data.firstname(), data.foto());
    }


public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {//функция обратится к БД и вернет результат.Функция выполняется в контексте сессии, которая автоматически открывается и закрывается
return session.createQuery("from GroupRecord", GroupRecord.class).list();//внутренняя функция
        }));
}
    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {//функция обратится к БД и вернет результат.Функция выполняется в контексте сессии, которая автоматически открывается и закрывается
            return session.createQuery("from ContactRecord", ContactRecord.class).list();//внутренняя функция
        }));
    }

    public long getGroupCount() {//считаем количество групп
        return (sessionFactory.fromSession(session -> {//функция обратится к БД и вернет результат.Функция выполняется в контексте сессии, которая автоматически открывается и закрывается
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();//внутренняя функция
        }));
    }

    public long getContactCount() {//считаем количество контактов
        return sessionFactory.fromSession(session -> {//функция обратится к БД и вернет результат.Функция выполняется в контексте сессии, которая автоматически открывается и закрывается
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();//внутренняя функция
        });
    }

    public void createGroup(GroupData groupData) {
sessionFactory.inSession(session -> {
    session.getTransaction().begin();//открываем транзакцию
 session.persist(convert(groupData));
    session.getTransaction().commit();//закрываем транзакцию
});
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();//открываем транзакцию
            session.persist(convert(contactData));
            session.getTransaction().commit();//закрываем транзакцию
        });
}

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {//получаем информацию о контактах
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);//запрос к БД, который по идентификатору группы находит все контакты, которые в эту группу включены.
            // Получить объект по идентификатору
            //перенесли конвертацию внутрь сессии, чтобы можно было получить информацию о связях (она в последнюю очередб)
        });

    }
}
