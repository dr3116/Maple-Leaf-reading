package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.BookShelf;

import dbu.DBUtil;
//理论上不可修改
public class BookShelfService {
	private List<BookShelf> bookShelfs;
	private DBUtil dbUtil;
	public BookShelfService() {
		bookShelfs = new ArrayList<BookShelf>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有书架信息
	 * @param sql 查询书架的sql语句
	 * @return 书架集合
	 */
	public List<BookShelf> getBookShelfs(String sql){
		try {
			//查询所有书架
			ResultSet rs = dbUtil.queryDate("select * from bookshelf");
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String bookName = rs.getString("book_name");
				BookShelf bookShelf = createBookShelf(userId,bookName);
				bookShelfs.add(bookShelf);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return bookShelfs;
	}
	/**
	 * 根据书架信息构造书架对象
	 */
	private BookShelf createBookShelf(int userId,String bookName) {
		BookShelf bookShelf = new BookShelf();
		bookShelf.setUserId(userId);
		bookShelf.setBookName(bookName);
		return bookShelf;
	}
	/**
	 * 添加书架
	 * @param bookShelf 待添加的书架对象
	 * @return 添加书架是否成功，成功返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean addBookShelf(BookShelf bookShelf) throws ClassNotFoundException, SQLException {
		String sql ="insert into bookshelf(user_id,book_name)values("+bookShelf.getUserId()+","+"'"+bookShelf.getBookName()+"'"+")";
		dbUtil.addDataToTable(sql);
		if(dbUtil.isExist("select * from book where user_id ="+bookShelf.getUserId()+"and book_name ="+"'"+bookShelf.getBookName()+"'")) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 根据书架信息删除指定书架信息
	 * @param bookName userId 书架信息
	 * @return 是否删除书架信息，成功删除返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean deleteBookShelf(int userId,String bookName) throws ClassNotFoundException, SQLException {
		String sql = "delete from bookshelf where user_id ="+userId+"and book_name="+"'"+bookName+"'";
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from book where user_id ="+userId+"and book_name ="+"'"+bookName+"'")) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 *  修改书架数据信息
	 *  @param bookShelf
	 *  @return 是否修改成功，成功修改返回true，否则返回false
	 *  @throws SQLException 
	 *  @throws ClassNotFoundException 
	 * revise
	 */
	/*
	public boolean reviseBookShelf(int userId,String bookName) throws ClassNotFoundException, SQLException {
		String sql = "update bookshelf set user_id ="+userId+",book_name="+"'"+bookName+"'";
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from book where user_id ="+userId+"and book_name ="+"'"+bookName+"'")) {
			return true;
		}else {
			return false;
		}
	}
	*/
}
