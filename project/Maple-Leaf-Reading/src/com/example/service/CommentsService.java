package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entity.Comments;

import dbu.DBUtil;

public class CommentsService {
	private List<Comments> commentss;
	private DBUtil dbUtil;
	public CommentsService() {
		commentss = new ArrayList<Comments>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有评论信息
	 * @param sql 查询评论的sql语句
	 * @return 评论集合
	 */
	public List<Comments> getComments(String sql){
		try {
			//查询所有评论
			ResultSet rs = dbUtil.queryDate("select * from comments");
			while(rs.next()) {
				int commentsId = rs.getInt("comments_id");
				int postId = rs.getInt("post_id");
				String content = rs.getString("content");
				int reviewersId = rs.getInt("reviewers_id");
				Date time = rs.getDate("time");
				int numberOfLikes = rs.getInt("number_of_likes");
				Comments comments = createComments(commentsId,postId,content,reviewersId,time,numberOfLikes);
				commentss.add(comments);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return commentss;
	}
	/**
	 * 根据评论信息构造评论对象
	 */
	private Comments createComments(int commentsId,int postId,String content,int reviewersId,Date time,int numberOfLikes) {
		Comments comments = new Comments();
		comments.setCommentsId(commentsId);
		comments.setPostId(postId);
		comments.setContent(content);
		comments.setReviewersId(reviewersId);
		comments.setTime(time);
		comments.setNumberOfLikes(numberOfLikes);
		return comments;
	}
	/**
	 * 添加评论
	 * @param comments 待添加的评论对象
	 * @return 添加是否成功，成功返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean addComments(Comments comments) throws ClassNotFoundException, SQLException {
		String sql = "insert into comments(comments_id,post_id,content,reviewers_id,time,number_of_likes)values("+
				comments.getCommentsId()+","+comments.getPostId()+","+"'"+comments.getContent()+"'"+","+comments.getReviewersId()+
				","+"'"+comments.getTime()+"'"+","+comments.getNumberOfLikes()+")";
		dbUtil.addDataToTable(sql);
		if(dbUtil.isExist("select * from comments where comments_id ="+comments.getCommentsId())) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 根据评论编号删除指定评论
	 * @param commentsId 评论编号
	 * @return 是否删除成功，成功删除返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean deleteCommentsById(int commentsId) throws ClassNotFoundException, SQLException {
		String sql = "delete from comments where comments_id ="+commentsId;
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from comments where comments_id ="+commentsId)) {
			return false;
		}else {
			return true;
		}
	}
	/**
	 *  修改评论数据信息
	 *  @param Comments
	 *  @return 是否修改成功，成功修改返回true，否则返回false
	 *  @throws SQLException 
	 *  @throws ClassNotFoundException 
	 * revise
	 */
	public boolean reviseComments(int commentsId,int postId,String content,int reviewersId,Date time,int numberOfLikes) throws ClassNotFoundException, SQLException {
		String sql = "update comments set comments_id ="+commentsId+",post_id="+postId+",content="+"'"+content+"'"+",reviewers_id="+reviewersId+
				",time"+"'"+time+"'"+",number_of_likes="+numberOfLikes;
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from comments where comments_id ="+commentsId)) {
			return true;
		}else {
			return false;
		}
	}
}
