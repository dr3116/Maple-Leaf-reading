package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.SearchRecords;

import dbu.DBUtil;
//理论上不可修改
public class SearchRecordsService {
	private List<SearchRecords> searchRecordss;
	private DBUtil dbUtil;
	public SearchRecordsService() {
		searchRecordss = new ArrayList<SearchRecords>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有搜索历史信息
	 * @param sql 查询搜索历史的sql语句
	 * @return 搜索历史集合
	 */
	public List<SearchRecords> getSearchRecords(String sql){
		try {
			//查询所有搜索历史
			ResultSet rs = dbUtil.queryDate("select * from searchrecoreds");
			while(rs.next()) {
				int searchId = rs.getInt("search_id");
				String searchHistory = rs.getString("search_history");
				int userId = rs.getInt("user_id");
				SearchRecords searchRecords = createSearchRecords(searchId,searchHistory,userId);
				searchRecordss.add(searchRecords);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return searchRecordss;
	}
	/**
	 * 根据搜索历史信息构造搜索历史对象
	 */
	private SearchRecords createSearchRecords(int searchId,String searchHistory,int userId) {
		SearchRecords searchRecords = new SearchRecords();
		searchRecords.setSearchId(searchId);
		searchRecords.setSearchHistory(searchHistory);
		searchRecords.setUserId(userId);
		return searchRecords;
	}
	/**
	 * 添加搜索历史
	 * @param searchRecords 
	 * @return 添加是否成功，成功返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean addSearchRecoreds(SearchRecords searchRecords) throws ClassNotFoundException, SQLException {
		String sql = "insert into searchrecoreds(search_id,search_history,user_id)values("+searchRecords.getSearchId()+","+
				"'"+searchRecords.getSearchHistory()+"'"+","+searchRecords.getUserId()+")";
		dbUtil.addDataToTable(sql);
		if(dbUtil.isExist("select * from searchrecoreds where search_id ="+searchRecords.getSearchId())) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 根据搜索编号删除指定搜索历史
	 * @param searchId
	 * @return 是否删除成功，成功删除返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean deleteSearchRecordsById(int searchId) throws ClassNotFoundException, SQLException {
		String sql = "delete from searchrecoreds where search_id ="+searchId;
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from searchrecoreds where search_id ="+searchId)) {
			return false;
		}else {
			return true;
		}
	}
}
