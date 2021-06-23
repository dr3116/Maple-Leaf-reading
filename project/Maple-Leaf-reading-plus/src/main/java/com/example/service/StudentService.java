package com.example.service;

import com.example.entity.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {
    List<Student> findAllStudents();
    List<Student> findAllStudentsBySignNumDesc();
    int insertStudent(int userId, String userName, String userPassword, String userPhoto, int num,String userSex,String userStyleText);
    int updateStudent(int userId, String userName, String userPhoto, int num,String userSex,String userStyleText);
    int deleteStudent(int userId);
    List<Student> findStudentByName(String userName);
    String findStudentNumber();
    List<Student> findAllStudentByPageF(int pageNum,int pageSize);
    PageInfo<Student> findAllStudentByPages(int pageNum, int pageSize);
    String findStudentPasswordByAccount(String userName);
    String findStudentIdByAccount(String userName);
    Student findStudentInfoByAccount(String userName);
    Student findStudentInfoById(int userId);
}
