package com.baichuan.hy.tool.dao;

import com.baichuan.hy.tool.domain.TFavoriteColorItem;

import java.util.List;

public interface TFavoriteColorItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFavoriteColorItem record);

    int insertSelective(TFavoriteColorItem record);

    TFavoriteColorItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFavoriteColorItem record);

    int updateByPrimaryKey(TFavoriteColorItem record);

    List<TFavoriteColorItem> selectByListId(int listId);
}