package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entity.BookReview;

import dbu.DBUtil;

public class BookReviewService {
	private List<BookReview> bookReviews;
	private DBUtil dbUtil;
	public BookReviewService() {
		bookReviews = new ArrayList<BookReview>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有书评信息
	 * @param sql 查询书评的sql语句
	 * @return 书评集合
	 */
	public List<BookReview> getBookReviews(String sql){
		try {
			//查询所有图书
			ResultSet rs = dbUtil.queryDate("select * from book_review");
			while(rs.next()) {
				int bookReviewId = rs.getInt("book_review_id");
				String bookName = rs.getString("book_name");
				int userId = rs.getInt("user_id");
				String content = rs.getString("content");
				double score = rs.getDouble("score");
				Date bookReviewTime = rs.getDate("book_review_time");
				BookReview bookReview = createBookReview(bookReviewId,bookName,userId,content,score,bookReviewTime);
				bookReviews.add(bookReview);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return bookReviews;
	}
	/**
	 * 根据书评信息构造书评对象
	 */
	private BookReview createBookReview(int bookReviewId,String bookName,int userId,String content,double score,Date bookReviewTime) {
		BookReview bookReview = new BookReview();
		bookReview.setBookReviewId(bookReviewId);
		bookReview.setBookName(bookName);
		bookReview.setUserId(userId);
		bookReview.setContent(content);
		bookReview.setScore(score);
		bookReview.setBookReviewTime(bookReviewTime);
		return bookReview;
	}
	/**
	 * 添加书评
	 * @param bookReview 待添加的书评对象
	 * @return 添加书评是否成功，成功返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean addBookReview(BookReview bookReview) throws ClassNotFoundException, SQLException {
		String sql = "insert into book_review(book_review_id,book_name,user_id,content,score,book_review_time)values("+
				bookReview.getBookReviewId()+","+"'"+bookReview.getBookName()+"'"+","+bookReview.getUserId()+","+
				"'"+bookReview.getContent()+"'"+","+bookReview.getScore()+","+"'"+bookReview.getBookReviewTime()+"'"+")";
		dbUtil.addDataToTable(sql);
		if(dbUtil.isExist("select * from book_review where book_review_id ="+bookReview.getBookReviewId())) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 根据书评编号删除指定书评
	 * @param bookReviewId 书评编号
	 * @return 是否删除书评，成功删除返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean deleteBookReviewById(int bookReviewId) throws ClassNotFoundException, SQLException {
		String sql = "delete from book where book_review_id ="+bookReviewId;
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from book_review where book_review_id ="+bookReviewId)) {
			return false;
		}else {
			return true;
		}
	}
	/**
	 *  修改书评数据信息
	 *  @param bookReview
	 *  @return 是否修改成功，成功修改返回true，否则返回false
	 *  @throws SQLException 
	 *  @throws ClassNotFoundException 
	 * revise
	 */
	public boolean reviseBookReview(int bookReviewId,String bookName,int userId,String content,double score,Date bookReviewTime) throws ClassNotFoundException, SQLException {
		String sql = "update book_review set book_review_id ="+bookReviewId+",book_name="+"'"+bookName+"'"+",user_id="+userId+",content="+
				"'"+content+"'"+",score="+score+",book_review_time="+"'"+bookReviewTime+"'";
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from book_review where book_review_id ="+bookReviewId)) {
			return true;
		}else {
			return false;
		}		
	}
	
	
	

	
	
}
