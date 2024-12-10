package ru.stqa.mantis.manager.developermail;

import ru.stqa.mantis.model.DeveloperMailUser;

import java.util.List;

public record GetIdsResponse(Boolean success, Object errors, List<String> result) {//класс который записывает ответы на добавление пользователя
}
