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
public void openPage (String email, String password) throws InterruptedException {//открываем браузер и заполняем форму создания и отправляем (в браузере, создать класс помощник) Письмо уходит
    openManage(); //тыкнуть на Manage
    openUsers();//перейти на вкладку Users
    createNewAccount();//открыть форму создания нового контакта
    fillContactForm();//заполнить форму создания контакта
    submitContact();//сохранение данных по контакту

}

    private void openManage() {//тыкнуть на Manage
        click(By.linkText("Manage"));
    }

    private void openUsers() {//перейти на вкладку Users
        click(By.cssSelector("[href='/mantisbt-2.26.3/manage_user_page.php']"));
        //click(By.linkText("manage_user_page"));
    }
    private void fillContactForm() {
            type(By.id ("email-field"), "%s@localhost");
            type(By.id ("user-username"),"username");
    }
    private void submitContact() {
        click(By.xpath("(//input[@type=\'submit\'])"));
    }
    private void createNewAccount() {
        click(By.cssSelector("[href='manage_user_create_page.php']"));
        //click(By.linkText("Create New Account"));//href="manage_user_create_page.php"
    }

    public void finalPage(String email, String password) {
//
    }

    public void login(String administrator, String root) {
        type(By.name("username"), administrator);
        click(By.xpath("//input[@value=\'Login\']"));
        type(By.id("password"),root);
        click(By.xpath("//input[@value=\'Login\']"));


    }
}


