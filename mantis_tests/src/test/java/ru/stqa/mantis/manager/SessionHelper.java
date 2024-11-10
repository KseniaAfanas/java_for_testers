package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase{
public SessionHelper(ApplicationManager manager){//конструктоп принимает на вход ApplicationManager
    super(manager);//и обращается к конструктору базового класса, передавая ссылку на менеджера в него
}

    public void login(String user, String password) {
    type(By.name("username"),user);//текст это имя пользователя, которое передано в качестве параметра
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"),password);
    click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {//проверяет сейчас прям выполнен логин или нет?
    return isElementPresent(By.cssSelector("span.user-info"));//проверяем наличие элемента, который доступен только внутри сессии
    }
}
