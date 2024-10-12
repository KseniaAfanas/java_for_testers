package manager;

import model.GroupData;
import org.openqa.selenium.By;

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

    public void removeGroup() {
        openGroupsPage();
        selectGroup();
        removeSelectedGroups();
        returnToGroupsPage();
    }
    public void modifyGroup(GroupData modifiedGroup) {//метод для модификации группы
        openGroupsPage();
        selectGroup();//выбрать группу (отметить галочкой)
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

    private void selectGroup() {
        click(By.name("selected[]"));
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

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox: checkboxes){ //цикл который перебирает все элементы коллекции
           checkbox.click();
        }
    }
}

