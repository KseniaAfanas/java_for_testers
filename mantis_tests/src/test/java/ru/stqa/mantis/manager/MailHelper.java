package ru.stqa.mantis.manager;

import jakarta.mail.*;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailHelper extends HelperBase{

    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String username, String password, Duration duration) {
        //цикл на проверку пришла ли почта. Завершаем его по истечении времени. Возвращает либо почту, либо выбрасывает исключение
        var start = System.currentTimeMillis();//запоминаем время выполнения начала метода
        while (System.currentTimeMillis()<start+duration.toMillis()){//пока тек время < (старт + Duration) выполняем действия
        try {
            var inbox = getInbox(username, password);//обращаемся к почтовому серверу
            inbox.open(Folder.READ_ONLY);//открываем ящик только на чтение
            var messages = inbox.getMessages();//читаем почту. Возвращается массив, необходимо преобразовать в список
            var result = Arrays.stream(messages)//запоминаем результат
                    .map(m -> {
                        try {
                            // Применяем map и передаем в качестве параметра трансформатор, который будет преобразовывать объекты одного тиа в другой.
                            // Обратный адрес m.getFrom(). Берем первого отправителя, тк их мб много. Преобразуем это в список
                            return new MailMessage()
                                    .withFrom(m.getFrom()[0].toString())
                                    .withContent((String) m.getContent());
                        } catch (MessagingException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();//превращаем массив в поток
            inbox.close();//закрываем почту
            inbox.getStore().close();//закрываем хранилище
            if (result.size()>0){//если список не пуст
                return result;//возвращаем результат
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
        throw new RuntimeException("No mail"); //если не получили почту, те цикл закончился, тогда выбрасываем исключение
    }

    private Folder getInbox(String username, String password) {
        try {
            var session = Session.getInstance(new Properties());//создать сессию для работы с почтовым сервером
            Store store = session.getStore("pop3");
            //получаем доступ к хранилищу почты
            store.connect("localhost", username, password);//устанавливаем соединение
            var inbox = store.getFolder("INBOX");//обращаемся к почтовому ящику
            return inbox;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
    }
}

public void drain(String username, String password){//удалить все письма из почтового ящика
     try {
        var inbox = getInbox(username, password);//передаем (username, password) чтобы очистить ящик только у заданного пользователя
        inbox.open(Folder.READ_WRITE);//открываем ящик на чтение и запись
        Arrays.stream(inbox.getMessages()).forEach(m-> {
            try {
                m.setFlag(Flags.Flag.DELETED,true);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });//помечаем каждое письмо как удаленное
        inbox.close();//закрываем inbox
        inbox.getStore().close();//закрываем хранилище
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
    }

}
