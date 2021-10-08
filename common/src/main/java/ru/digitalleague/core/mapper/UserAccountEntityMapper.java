package ru.digitalleague.core.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import ru.digitalleague.core.model.UserAccountEntity;

@Mapper
@Repository
public interface UserAccountEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAccountEntity record);

    int insertSelective(UserAccountEntity record);

    UserAccountEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAccountEntity record);

    int updateByPrimaryKey(UserAccountEntity record);

    Optional<UserAccountEntity> findByLogin(String login);

    @SelectProvider(value = SqlProvider.class, method = "test")
    UserAccountEntity findTestSqlInjection(String sql);
}
