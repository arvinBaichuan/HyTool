package com.baichuan.hy.tool.dao;

import com.baichuan.hy.tool.domain.TFavoriteCronList;

import java.util.List;

public interface TFavoriteCronListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFavoriteCronList record);

    int insertSelective(TFavoriteCronList record);

    TFavoriteCronList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFavoriteCronList record);

    int updateByPrimaryKey(TFavoriteCronList record);

    List<TFavoriteCronList> selectAll();

    TFavoriteCronList selectByTitle(String selectedItem);
}