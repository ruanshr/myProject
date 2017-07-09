package com.project.weixin.dao;

import com.project.weixin.model.WxUsers;

public interface WxUsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxUsers record);

    int insertSelective(WxUsers record);

    int updateByPrimaryKeySelective(WxUsers record);

    int updateByPrimaryKey(WxUsers record);
}