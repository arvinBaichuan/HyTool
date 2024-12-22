package com.baichuan.hy.tool.dao;

import com.baichuan.hy.tool.domain.TQrCode;

public interface TQrCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TQrCode record);

    int insertSelective(TQrCode record);

    TQrCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TQrCode record);

    int updateByPrimaryKey(TQrCode record);
}