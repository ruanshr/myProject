package com.project.weixin.dao;

import com.project.weixin.model.WxAccessToken;

public interface WxAccessTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxAccessToken record);

    int insertSelective(WxAccessToken record);

    int updateByPrimaryKeySelective(WxAccessToken record);

    int updateByPrimaryKey(WxAccessToken record);
}