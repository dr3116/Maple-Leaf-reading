package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entity.RecentReading;

import dbu.DBUtil;
//理论上只能查
public class RecentReadingService {
	private List<RecentReading> recentReadings;
	private DBUtil dbUtil;
	public RecentReadingService() {
		recentReadings = new ArrayList<RecentReading>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有最近阅读信息
	 * @param sql 查询最近阅读的sql语句
	 * @return 最近阅读集合
	 */
	public List<RecentReading> getRecentReadings(String sql){
		try {
			//查询所有最近阅读信息
			ResultSet rs = dbUtil.queryDate("select * from recent_reading");
			while(rs.next()) {
				int recentReadingId = rs.getInt("recent_reading_id");
				int userId = rs.getInt("user_id");
				String bookName = rs.getString("book_name");
				Date lastReadingTime = rs.getDate("last_reading_time");
				RecentReading recentReading = createRecentReading(recentReadingId,userId,bookName,lastReadingTime);
				recentReadings.add(recentReading);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return recentReadings;
	}
	/**
	 * 根据最近阅读信息构造最近阅读对象
	 */
	private RecentReading createRecentReading(int recentReadingId,int userId,String bookName,Date lastReadingTime) {
		RecentReading recentReading = new RecentReading();
		recentReading.setRecentReadingId(recentReadingId);
		recentReading.setUserId(userId);
		recentReading.setBookName(bookName);
		recentReading.setLastReadingTime(lastReadingTime);
		return recentReading;
	}
}
