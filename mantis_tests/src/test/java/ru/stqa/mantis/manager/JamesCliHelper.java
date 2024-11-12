package ru.stqa.mantis.manager;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;
import ru.stqa.mantis.model.MailMessage;

public class JamesCliHelper extends HelperBase{
    public JamesCliHelper (ApplicationManager manager) {
        super(manager);
}
public void addUser(String email, String password){ //метод, который вызывает команду "java -cp "james-server-jpa-app.lib/*" org.apache.james.cli.ServerCmd AddUser"
    CommandLine cmd = new CommandLine(
            "java","-cp","\"james-server-jpa-app.lib/*\"",
            "org.apache.james.cli.ServerCmd",
            "AddUser", email,password);
    cmd.setWorkingDirectory(manager.property("james.workingDir"));//установить рабочую директорию:значение перенесли в пропертис
    CircularOutputStream out = new CircularOutputStream();
    cmd.copyOutputTo(out);//перехватываем сообщение об ошибке, чтобы понять почему пользователь НЕ добавлен
    cmd.execute();//запустить команду
    cmd.waitFor();//подождать пока она доработает до конца
    System.out.println(out);//печатаем на консоль то, что перехватили

}
public void openPage (String email, String password) {//открываем браузер и заполняем форму создания и отправляем (в браузере, создать класс помощник) Письмо уходит
    openManage(); //открыть страницу manage_overview_page
    openUsers();//перейти на вкладку Users
    createNewAccount();//открыть форму создания нового контакта
    fillContactForm();//заполнить форму создания контакта
    submitContact();//сохранение данных по контакту

}

    private void submitContact() {
        click(By.linkText("Create User"));
    }

    private void fillContactForm() {
            type(By.name("E-mail"), "%s@localhost");
            type(By.name("Username"),"username");

    }

    private void createNewAccount() {
        click(By.linkText("Create New Account"));
    }

    private void openUsers() {
        click(By.linkText("User"));
        }

    private void openManage() {
        click(By.name("Manage"));
    }

    public void finalPage(String email, String password) {

    }
}


