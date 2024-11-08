package ru.stqa.addressbook.manager;

import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class GroupHelper extends HelperBase {

    public GroupHelper (ApplicationManager manager){
        super(manager);
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }
    /*public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));// удалили, тк нигде не используется
    }*/

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroup(GroupData group) {//в качесте параметра принимает объект типа GroupData
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupsPage();
    }
    public void modifyGroup(GroupData group, GroupData modifiedGroup) {//метод для модификации группы
        openGroupsPage();
        selectGroup(group);//выбрать группу (отметить галочкой)
        initGroupModification();//нажать кнопку модификации Edit
        fillGroupForm(modifiedGroup);//заполнить форму данными, которые содержатся в переданном объекте
        submitGroupModification();//сохраняем форму
        returnToGroupsPage();
    }


    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void removeSelectedGroups() {
        click(By.name("delete"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));

    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup(GroupData group) {//параметр-объект типа GroupData, который содержит идентификатор нужной группы
        click(By.cssSelector(String.format("input[value='%s']",group.id())));
    }

    public int getCount() {
        openGroupsPage();//открытие страницы со списком групп
        return manager.driver.findElements(By.name("selected[]")).size(); //метод, который находит много элеметов. Возвращает список. size()- возвращает размер списка
    }

    public void removeAllGroups() {//метод для удаления всех групп
        openGroupsPage();//открытие страницы со списком групп
        selectAllGroups();//вспомогательный метод для выбора всех групп
        removeSelectedGroups();
        }

    /*private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox: checkboxes){ //цикл который перебирает все элементы коллекции
           checkbox.click();
        }
    }*/

        private void selectAllGroups() {//переписали с использованием функционального стиля программирования
            manager.driver
                    .findElements(By.name("selected[]"))//найти элементы
                    .forEach(WebElement::click);//цикл заменили на эту конструкцию: для каждого элемента списка checkboxes будет вызван метод click, описанный в классе WebElement
    }

    public List<GroupData> getList() {
        openGroupsPage();//открытие страницы со списком групп
        var groups = new ArrayList<GroupData>(); //цикл, который читает данные из ИБ, анализирует их и строит список. Создаем пустой список
        var spans = manager.driver.findElements(By.cssSelector("span.group"));//получить со страницы список элементов, которые содержат информацию о группах
        for (var span:spans){
            var name = span.getText();//название группы это текст, поэтому его получаем с помощью getText
var checkbox = span.findElement(By.name("selected[]")); //найдем чекбокс, который находится внутри элемента span
            var id= checkbox.getAttribute("value");//получаем идентификатор
            groups.add(new GroupData().WithId(id).WithName(name));// в список groups добавляем новый объект
        }
        return groups;
    }
}

