package com.project.weixin.dao;

import com.project.weixin.model.WxAuthorize;

public interface WxAuthorizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxAuthorize record);

    int insertSelective(WxAuthorize record);

    int updateByPrimaryKeySelective(WxAuthorize record);

    int updateByPrimaryKey(WxAuthorize record);
}