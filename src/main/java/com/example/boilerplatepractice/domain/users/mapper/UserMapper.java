package com.example.boilerplatepractice.domain.users.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper
public interface UserMapper {
    List<UUID> selectInvalidUserIds();
}