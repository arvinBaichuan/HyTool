package com.baichuan.hy.tool.dao;

import com.baichuan.hy.tool.domain.TFavoriteRegexList;

import java.util.List;

public interface TFavoriteRegexListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFavoriteRegexList record);

    int insertSelective(TFavoriteRegexList record);

    TFavoriteRegexList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFavoriteRegexList record);

    int updateByPrimaryKey(TFavoriteRegexList record);

    List<TFavoriteRegexList> selectAll();

    TFavoriteRegexList selectByTitle(String selectedItem);
}