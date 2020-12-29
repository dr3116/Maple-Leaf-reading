package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entity.Book;

import dbu.DBUtil;

public class BookService {
	private List<Book> books;
	private DBUtil dbUtil;
	public BookService() {
		books = new ArrayList<Book>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有图书信息
	 * @param sql 查询图书的sql语句
	 * @return 图书集合
	 */
	public List<Book> getBooks(String sql){
		try {
			//查询所有图书
			ResultSet rs = dbUtil.queryDate(sql);
			while(rs.next()) {
				String bookName = rs.getString("book_name");
				String classification = rs.getString("classification");
				int readingVolume = rs.getInt("reading_volume");
				int numberOfChapters = rs.getInt("number_of_chapters");
				Date releaseTime = rs.getDate("release_time");
				String bookPhoto = rs.getString("book_photo");
				double bookRating = rs.getDouble("book_rating");
				String author = rs.getString("author");
				int numberOfCollections = rs.getInt("number_of_collections");
				String briefIntroduction = rs.getString("brief_introduction");
				Book book = createBook(bookName,classification,readingVolume,numberOfChapters,releaseTime,bookPhoto,bookRating,author,numberOfCollections,briefIntroduction);
				books.add(book);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	/**
	 * 根据图书信息构造图书对象
	 */
	private Book createBook(String bookName,String classification,int readingVolume,int numberOfChapters,Date releaseTime,String bookPhoto,double bookRating,String author,int numberOfCollections,String briefIntroduction) {
		Book book = new Book();
		book.setBookName(bookName);
		book.setClassification(classification);
		book.setReadingVolume(readingVolume);
		book.setNumberOfChapters(numberOfChapters);
		book.setReleaseTime(releaseTime);
		book.setBookPhoto(bookPhoto);
		book.setBookRating(bookRating);
		book.setAuthor(author);
		book.setNumberOfCollections(numberOfCollections);
		book.setBriefIntroduction(briefIntroduction);
		return book;
	}
	/**
	 * 添加图书
	 * @param book 待添加的图书对象
	 * @return 添加图书是否成功，成功返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean addBook(Book book) throws ClassNotFoundException, SQLException {
		String sql = "insert into book(book_name,classification,reading_volume,number_of_chapters,release_time,book_photo,book_rating,author,number_of_collections,brief_introduction)values("+
				"'"+book.getBookName()+"'"+","+"'"+book.getClassification()+"'"+","+book.getReadingVolume()+","+book.getNumberOfChapters()+","+"'"+book.getReleaseTime()+"'"+","+"'"+book.getBookPhoto()+
				"'"+","+book.getBookRating()+","+"'"+book.getAuthor()+"'"+","+book.getNumberOfCollections()+","+"'"+book.getBriefIntroduction()+"'"+")";
		dbUtil.addDataToTable(sql);
		if(dbUtil.isExist("select * from book where book_name ="+"'"+book.getBookName()+"'")) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 根据图书名称删除指定图书
	 * @param bookName 图书名称
	 * @return 是否删除图书，成功删除返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean deleteBookByName(String bookName) throws ClassNotFoundException, SQLException {
		//删除book数据库里的图书
		String sql = "delete from book where book_name = "+"'"+bookName+"'";
		dbUtil.updateData(sql);
		//删除book_review数据库里的图书
		sql = "delete from book_review where book_name = "+"'"+bookName+"'";
		dbUtil.updateData(sql);
		//删除bookshelf数据库里的图书
		sql = "delete from bookshelf where book_name = "+"'"+bookName+"'";
		dbUtil.updateData(sql);
		//删除classifications数据库里的图书
		sql="delete from classifications where book_name = "+"'"+bookName+"'";
		dbUtil.updateData(sql);
		//删除post数据库里的图书
		sql="delete from post where book_name = "+"'"+bookName+"'";
		dbUtil.updateData(sql);
		//删除recent_reading数据库里的图书
		sql="delete from recent_reading where book_name = "+"'"+bookName+"'";
		dbUtil.updateData(sql);		
		
		if(dbUtil.isExist("select * from book where book_name ="+"'"+bookName+"'")) {
			return false;
		}
		return true;
	}
	/**
	 *  修改图书数据信息
	 *  @param bookName 图书ID
	 *  @return 是否修改成功，成功修改返回true，否则返回false
	 *  @throws SQLException 
	 *  @throws ClassNotFoundException 
	 * revise
	 */
	public boolean reviseBook(String bookName,String classification,int readingVolume,int numberOfChapters,Date releaseTime,String bookPhoto,double bookRating,String author,int numberOfCollections,String briefIntroduction)throws ClassNotFoundException ,SQLException {
		String sql = "update book set book_name ="+"'"+bookName+"'"+",classification="+"'"+classification+"'"+",reading_volume="+readingVolume+",number_of_chapters="+numberOfChapters+
				",release_time="+"'"+releaseTime+"'"+",book_photo="+"'"+bookPhoto+"'"+",book_rating="+bookRating+",author="+"'"+author+"'"+",number_of_collections="+numberOfCollections+
				",brief_introduction="+"'"+briefIntroduction+"'"+"where book_name ="+"'"+bookName+"'";
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from book where book_name ="+"'"+bookName+"'")) {
			return true;
		}else {
			return false;
		}		
	}
	
	
	/**
	 *  修改图书数据信息,但是不修改图片
	 *  @param bookName 图书ID
	 *  @return 是否修改成功，成功修改返回true，否则返回false
	 *  @throws SQLException 
	 *  @throws ClassNotFoundException 
	 * revise
	 */
	public boolean reviseBookWithoutImage(String bookName,String classification,int readingVolume,int numberOfChapters,Date releaseTime,double bookRating,String author,int numberOfCollections,String briefIntroduction)throws ClassNotFoundException ,SQLException {
		String sql = "update book set book_name ="+"'"+bookName+"'"+",classification="+"'"+classification+"'"+",reading_volume="+readingVolume+",number_of_chapters="+numberOfChapters+
				",release_time="+"'"+releaseTime+"'"+",book_rating="+bookRating+",author="+"'"+author+"'"+",number_of_collections="+numberOfCollections+
				",brief_introduction="+"'"+briefIntroduction+"'"+"where book_name ="+"'"+bookName+"'";
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from book where book_name ="+"'"+bookName+"'")) {
			return true;
		}else {
			return false;
		}		
	}
	
	
	
	
	
	
	//用于booklist1，查询所有图书数量
	int booksNumber=0;
	
	public int getBooksNumber(){
		try {
			//查询所有图书
			ResultSet rs = dbUtil.queryDate("select count(*) from book");
			while(rs.next()) {
				booksNumber = rs.getInt(1);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return  booksNumber;
	}
	
	
	//根据书名查询有没有那本图书
	public int findBook(String bookName) throws ClassNotFoundException, SQLException{
		if(dbUtil.isExist("select * from book where book_name ="+"'"+bookName+"'")) {
			return 1;
		}else {
			return 0;
		}
	}
	
	
	
	//根据书名修改照片名字
	public int changeBookImageNameByBookName(String bookName,String imageName) throws ClassNotFoundException, SQLException{
		String sql="update book set book_photo='"+imageName+"' where book_name='"+bookName+"'";
		System.out.println("sql=="+sql);
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from book where book_name ="+"'"+bookName+"'")) {
			return 1;
		}else {
			return 0;
		}
	}
	
	
	
	public int getClassNumber(String str1,String str2,String str3,String str4,String str5){
		try {
			//查询所有图书
			ResultSet rs = dbUtil.queryDate("select count(*) from book where classification in('"+str1+"','"+str2+"','"+str3+"','"+str4+"','"+str5+"') ");
			while(rs.next()) {
				booksNumber = rs.getInt(1);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return  booksNumber;
	}

	
	
	
}
