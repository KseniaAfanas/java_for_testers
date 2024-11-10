package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

import java.nio.file.Paths;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver().findElement(locator).click();// обращаемся к driver как к методу driver(), тк сделали ленивую инициализацию для него
    }

    protected void type(By locator, String text) {//метод для заполнения поля с контактными данными
        click(locator);
        manager.driver().findElement(locator).clear();//очистить поле ввода
        manager.driver().findElement(locator).sendKeys(text);
    }
    protected void attach(By locator, String file) {//метод для просмотра картинок
        manager.driver().findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());//класс, который помогает работать с путями:преобразовываем относильный путь в абсолютный
    }

    protected boolean isElementPresent(By locator){
        return manager.driver().findElements(locator).size()>0;//если размер больше 0, значит элемент присутствует
    }
}
