package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase{//помощник для работы с БД
    public JdbcHelper (ApplicationManager manager){//получаем доступ к менеджеру
        super(manager);
    }//инициализируем менеджера и ссылка гп самого себя будет сохранена внутри помощника

    public List<GroupData> getGroupList() {
        var groups=new ArrayList<GroupData>();//создаем пусто список
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement=conn.createStatement();//устанавливаем соединение с БД
            var result = statement.executeQuery("SELECT group_id,group_name,group_header,group_footer FROM group_list"))//запрос инфо из БД
            {
            while (result.next()){//пока результаты не кончились
                groups.add(new GroupData()
                        .WithId(result.getString("group_id"))
                        .WithName(result.getString("group_name"))
                        .WithHeader(result.getString("group_header"))
                        .WithFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;//возвращаем список полученный из БД
    }

    public List<ContactData> getContactList() {
        var contacts=new ArrayList<ContactData>();//создаем пусто список
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement=conn.createStatement();//устанавливаем соединение с БД
             var result = statement.executeQuery("SELECT id,middlename,lastname, nickname,mobile, email, firstname, photo FROM addressbook"))//запрос инфо из БД

        {
            while (result.next()){//пока результаты не кончились
                contacts.add(new ContactData()
                        .WithId(result.getString("id"))
                        .WithMiddlename(result.getString("middlename"))
                        .WithLastname(result.getString("lastname"))
                        .WithNickname(result.getString("nickname"))
                        .WithMobile(result.getString("mobile"))
                        .WithEmail(result.getString("email"))
                        .WithFirstname(result.getString("firstname"))
                        .WithFoto(""));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;//возвращаем список полученный из БД
    }

    public void checkConsistency() {//ничего не возвращает, а выбрасывает исключение, если возникли какие-то проблемы
        //проверяем вернулся ли какой-нибудь результат из соотвествующего select. Если ДА, то БЕДА)
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement=conn.createStatement();//устанавливаем соединение с БД
             var result = statement.executeQuery("SELECT * FROM address_in_groups ag LEFT JOIN addressbook ab ON ab.id=ag.id WHERE ab.id IS NULL"))//запрос инфо из БД
        {
if (result.next()){//если список результатов НЕ пустой
    throw new IllegalStateException("DB is corrupted");//БД повреждена
}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
