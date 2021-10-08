package ru.digitalleague.core.service;

import ru.digitalleague.core.model.UserAccountEntity;

public interface UserAccountService {

    UserAccountEntity registration(UserAccountEntity userAccountEntity);

    UserAccountEntity test(String login);
}
