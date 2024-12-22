package com.baichuan.hy.tool.dao;

import com.baichuan.hy.tool.domain.THttpRequestHistory;

import java.util.List;

public interface THttpRequestHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(THttpRequestHistory record);

    int insertSelective(THttpRequestHistory record);

    THttpRequestHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(THttpRequestHistory record);

    int updateByPrimaryKey(THttpRequestHistory record);

    List<THttpRequestHistory> selectAll();

    List<THttpRequestHistory> selectByRequestId(Integer requestId);
}