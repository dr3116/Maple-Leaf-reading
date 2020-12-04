package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.example.entity.Post;

import dbu.DBUtil;

public class PostService {
	private List<Post> posts;
	private DBUtil dbUtil;
	public PostService() {
		posts = new ArrayList<Post>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有帖子信息
	 * @param sql 查询帖子的sql语句
	 * @return 帖子集合
	 */
	public List<Post> getPosts(String sql){
		try {
			//查询所有帖子
			ResultSet rs = dbUtil.queryDate("select * from post");
			while(rs.next()) {
				int postId = rs.getInt("post_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String photo = rs.getString("photo");
				String bookName = rs.getString("book_name");
				int numberOfLikes = rs.getInt("number_of_likes");
				int comments = rs.getInt("comments");
				int userId = rs.getInt("user_id");
				Date postTime = rs.getDate("post_time");
				Post post = createPost(postId,title,content,photo,bookName,numberOfLikes,comments,userId,postTime);
				posts.add(post);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	/**
	 * 根据帖子信息构造帖子对象
	 */
	private Post createPost(int postId,String title,String content,String photo,String bookName,int numberOfLikes,int comments,int userId,Date postTime) {
		Post post = new Post();
		post.setPostId(postId);
		post.setTitle(title);
		post.setContent(content);
		post.setPhoto(photo);
		post.setBookName(bookName);
		post.setNumberOfLikes(numberOfLikes);
		post.setComments(comments);
		post.setUserId(userId);
		post.setPostTime(postTime);
		return post;
	}
	/**
	 * 添加帖子
	 * @param post 待添加的帖子对象
	 * @return 添加是否成功，成功返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean addPost(Post post) throws ClassNotFoundException, SQLException {
		String sql = "insert into post(post_id,title,content,photo,book_name,number_of_likes,comments,user_id)values("+
				post.getPostId()+","+"'"+post.getTitle()+"'"+","+"'"+post.getContent()+"'"+","+"'"+post.getPhoto()+"'"+
				","+"'"+post.getBookName()+"'"+","+post.getNumberOfLikes()+","+post.getComments()+","+post.getUserId()+","+"'"+post.getPostTime()+"'"+")";
		dbUtil.addDataToTable(sql);
		if(dbUtil.isExist("select * from post where post_id ="+post.getPostId())) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 根据帖子编号删除指定帖子
	 * @param postId 帖子编号
	 * @return 是否删除成功，成功删除返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean deletePostbyId(int postId) throws ClassNotFoundException, SQLException {
		String sql = "delete from post where post_id ="+postId;
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from post where post_id ="+postId)) {
			return false;
		}else {
			return true;
		}
	}
	/**
	 *  修改帖子数据信息
	 *  @param Post
	 *  @return 是否修改成功，成功修改返回true，否则返回false
	 *  @throws SQLException 
	 *  @throws ClassNotFoundException 
	 * revise
	 */
	public boolean revisePost(int postId,String title,String content,String photo,String bookName,int numberOfLikes,int comments,int userId,Date postTime) throws ClassNotFoundException, SQLException {
		String sql = "update post set post_id ="+postId+",title="+"'"+title+"'"+",content="+"'"+content+"'"+",photo="+"'"+photo+"'"+
				",book_name="+"'"+bookName+"'"+",number_of_likes="+numberOfLikes+",comments="+comments+",user_id="+userId+",post_time="+"'"+postTime+"'";
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from post where post_id ="+postId)) {
			return true;
		}else {
			return false;
		}
	}
}
