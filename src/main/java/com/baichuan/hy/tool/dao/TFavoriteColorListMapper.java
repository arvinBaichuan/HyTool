package com.baichuan.hy.tool.dao;

import com.baichuan.hy.tool.domain.TFavoriteColorList;

import java.util.List;

public interface TFavoriteColorListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFavoriteColorList record);

    int insertSelective(TFavoriteColorList record);

    TFavoriteColorList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFavoriteColorList record);

    int updateByPrimaryKey(TFavoriteColorList record);

    List<TFavoriteColorList> selectAll();

    TFavoriteColorList selectByTitle(String title);
}