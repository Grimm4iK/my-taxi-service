package ru.digitalleague.core.mapper;

public class SqlProvider {

    public String test(String sql) {

        return "select * from user_account where login = \'" + sql + '\'';
    }
}
