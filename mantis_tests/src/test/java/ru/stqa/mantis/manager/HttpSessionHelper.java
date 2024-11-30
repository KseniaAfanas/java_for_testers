package ru.stqa.mantis.manager;

import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;

public class HttpSessionHelper extends HelperBase{//работает на уровне протокола HTTP
    OkHttpClient client;
    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();//проинициализировали клиента. Устанавливаем кукименеджера
    }

    public void login(String username, String password) {//отправляем форму с вебстраницы
        RequestBody formBody = new FormBody.Builder()
                .add("username",username)//имя пользователя
                .add("password",password)//пароль
                .build();//метод формирует значение нужного типа и помещает её в переменную formBody
        Request request = new Request.Builder()
                .url(String.format("%s/login.php",manager.property("web.baseUrl")))//тк в пропертис для web.baseUrl указано http://localhost/mantisbt-2.26.3/
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginUser(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username",username)//имя пользователя
                .add("password",password)//пароль
                .build();//метод формирует значение нужного типа и помещает её в переменную formBody
        Request request = new Request.Builder()
                .url(String.format("%s/login_page.php",manager.property("web.baseUrl")))//тк в пропертис для web.baseUrl указано http://localhost/mantisbt-2.26.3/
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn() {//проверяет сейчас прям выполнен логин или нет, отправляем GET запрос
        Request request = new Request.Builder()
                .url(manager.property("web.baseUrl"))//для GET запроса тип указывать НЕ обязательно
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();//созраняем ответ, чтобы его анализировать. body() возвращает объект
            return body.contains("<span class=\"user-info\">administrator</span>");//проверяем в ответе блок в котором лгин действительно выполнился
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedUserIn(String username) {//проверяет сейчас прям выполнен логин или нет, отправляем GET запрос
        Request request = new Request.Builder()
                .url(manager.property("web.baseUrl"))//для GET запроса тип указывать НЕ обязательно
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();//созраняем ответ, чтобы его анализировать. body() возвращает объект
            return body.contains("<span class=\"user-info\">" + username + "</span>");//проверяем в ответе блок в котором лгин действительно выполнился
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
