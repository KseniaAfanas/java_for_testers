package manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper (ApplicationManager manager){//
        super(manager);//параметр manager передается в конструктор базового класса
    }
    void login(String user, String password) {
        type(By.name("user"),user);
        type(By.name("pass"),password);
        click(By.xpath("//input[@value=\'Login\']"));
    }
}
