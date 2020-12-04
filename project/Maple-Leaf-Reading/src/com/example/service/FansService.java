package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Fans;

import dbu.DBUtil;
//逻辑上不可修改
public class FansService {
	private List<Fans> fanss;
	private DBUtil dbUtil;
	public FansService() {
		fanss = new ArrayList<Fans>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有粉丝信息
	 * @param sql 查询粉丝的sql语句
	 * @return 评论集合
	 */
	public List<Fans> getFans(String sql){
		try {
			//查询所有粉丝信息
			ResultSet rs = dbUtil.queryDate("select * from fans");
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				int peopleConcernedId = rs.getInt("people_concerned_id");
				Fans fans = createFans(userId,peopleConcernedId);
				fanss.add(fans);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return fanss;
	}
	/**
	 * 根据粉丝信息构造粉丝对象
	 */
	private Fans createFans(int userId,int peopleConcernedId) {
		Fans fans = new Fans();
		fans.setUserId(userId);
		fans.setPeopleConcernedId(peopleConcernedId);
		return fans;
	}
	/**
	 * 添加粉丝信息
	 * @param fans 待添加的粉丝对象
	 * @return 添加是否成功，成功返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean addFans(Fans fans) throws ClassNotFoundException, SQLException {
		String sql = "insert into fans(user_id,people_concerned_id)values("+fans.getUserId()+","+fans.getPeopleConcernedId()+")";
		dbUtil.addDataToTable(sql);
		if(dbUtil.isExist("select * from fans where user_id ="+fans.getUserId()+"and people_concerned_id ="+fans.getPeopleConcernedId())) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 根据粉丝信息删除指定评论
	 * @param Fans
	 * @return 是否删除成功，成功删除返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean deleteFans(int userId,int peopleConcernedId) throws ClassNotFoundException, SQLException {
		String sql = "delete from fans where user_id ="+userId+"and people_concerned_id="+peopleConcernedId;
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from fans where user_id ="+userId+"and people_concerned_id ="+peopleConcernedId)) {
			return false;
		}else {
			return true;
		}
	}
}
