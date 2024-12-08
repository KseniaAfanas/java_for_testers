package ru.stqa.mantis.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import ru.stqa.mantis.manager.developermail.AddUserResponse;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.io.IOException;
import java.net.CookieManager;

public class DeveloperMailHelper extends HelperBase {
    public static final MediaType JSON = MediaType.get("application/json");
    OkHttpClient client;

    public DeveloperMailHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();//проинициализировали клиента. Устанавливаем кукименеджера

    }

    public DeveloperMailUser addUser() {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url("https://www.developermail.com/api/v1/mailbox")
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);//проверка того что ответ был успешным
            var text = response.body().string();//анализируем тело ответа
            var addUserResponse = new ObjectMapper().readValue(text, AddUserResponse.class);//text - источник; AddUserResponse.class - тип данных
            if (!addUserResponse.success()) {
                throw new RuntimeException(addUserResponse.errors().toString());
            }
            return addUserResponse.result();//вернулась инфа о созданном пользователе
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(DeveloperMailUser user) {
        Request request = new Request.Builder()
                        .url(String.format("https://www.developermail.com/api/v1/mailbox/%s",user.name()))
                        .header("X-MailboxToken",user.token())
                        .delete()
                        .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);//проверка того что ответ был успешным
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
