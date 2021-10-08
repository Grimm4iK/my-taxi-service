package ru.digitalleague.core.service;

import ru.digitalleague.core.model.UserAccountEntity;

import java.util.List;

public interface UserAccountService {

    UserAccountEntity registration(UserAccountEntity userAccountEntity);

    UserAccountEntity test(String login);
}
