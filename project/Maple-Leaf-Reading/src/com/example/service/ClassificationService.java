package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Classification;

import dbu.DBUtil;
//只有增删查，逻辑上不需要写改
public class ClassificationService {
	private List<Classification> classifications;
	private DBUtil dbUtil;
	public ClassificationService() {
		classifications = new ArrayList<Classification>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有分类信息
	 * @param sql 查询分类的sql语句
	 * @return 分类集合
	 */
	public List<Classification> getClassifications(String sql){
		try {
			//查询所有分类
			ResultSet rs = dbUtil.queryDate("select * from classifications");
			while(rs.next()) {
				String classification = rs.getString("classification");
				String bookName = rs.getString("book_name");
				String category = rs.getString("category");
				Classification classification1 = createClassification(classification, bookName, category);
				classifications.add(classification1);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return classifications;
	}
	/**
	 * 根据分类信息构造分类对象
	 */
	private Classification createClassification(String classification,String bookName,String category) {
		Classification classification1 = new Classification();
		classification1.setClassification(classification);
		classification1.setBookName(bookName);
		classification1.setCategory(category);
		return classification1;
	}
	/**
	 * 添加分类
	 * @param classification
	 * @return 添加分类是否成功，成功返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean addClassification(Classification classifications) throws ClassNotFoundException, SQLException {
		String sql = "insert into classifications(classifiation,book_name,category)values("+"'"+classifications.getClassification()+"'"+","+"'"+classifications.getBookName()+"'"
				+","+"'"+classifications.getCategory()+"'"+")";
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from classifications where classification ="+"'"+classifications.getClassification()+"'"+"and book_name ="+"'"+classifications.getBookName()+"'")) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 根据分类信息删除指定图书
	 * @param bookName 图书名称 classification 
	 * @return 是否删除图书，成功删除返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean deleteClassification(String classification,String bookName) throws ClassNotFoundException, SQLException {
		String sql = "delete from classifications where classification ="+"'"+classification+"'"+"and book_name="+"'"+bookName+"'";
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from classifications where classification ="+"'"+classification+"'"+"and book_name ="+"'"+bookName+"'")) {
			return false;
		}else {
			return true;
		}
	}
	
	
}
