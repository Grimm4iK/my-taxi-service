package ru.digitalleague.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import ru.digitalleague.core.model.AuthorityEntity;

@Mapper
@Repository
public interface AuthorityEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthorityEntity record);

    int insertSelective(AuthorityEntity record);

    AuthorityEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthorityEntity record);

    int updateByPrimaryKey(AuthorityEntity record);

    int insertAll(@Param("list") List<AuthorityEntity> list);
}
