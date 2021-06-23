package com.example.service.impl;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Student> findAllStudents() {
        return studentMapper.findAllStudents();
    }
    @Override
    public List<Student> findAllStudentsBySignNumDesc() {
        return studentMapper.findAllStudentsBySignNumDesc();
    }

    @Override
    public int insertStudent(int userId, String userName, String userPassword, String userPhoto, int num,String userSex,String userStyleText) {
        studentMapper.insertStudent(userId,userName,userPassword,userPhoto,num,userSex,userStyleText);
        return userId;
    }

    @Override
    public int updateStudent(int userId, String userName, String userPhoto, int num,String userSex,String userStyleText) {
        studentMapper.updateStudent(userId,userName,userPhoto,num,userSex,userStyleText);
        return userId;
    }

    @Override
    public int deleteStudent(int userId) {
        studentMapper.deleteStudent(userId);
        return userId;
    }

    @Override
    public List<Student> findStudentByName(String userName) {
        return studentMapper.findStudentByName(userName);
    }

    @Override
    public String findStudentNumber() {
        return  studentMapper.findStudentNumber();
    }

    @Override
    public List<Student> findAllStudentByPageF(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> lists = studentMapper.findAllStudents();
        return lists;
    }

    @Override
    public PageInfo<Student> findAllStudentByPages(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> lists = studentMapper.findAllStudents();
        PageInfo<Student> pageInfo = new PageInfo<Student>(lists);
        return pageInfo;
    }


    @Override
    public String findStudentPasswordByAccount(String userName) {
        return studentMapper.findStudentPasswordByAccount(userName);
    }

    @Override
    public String findStudentIdByAccount(String userName) {
        return studentMapper.findStudentIdByAccount(userName);
    }

    @Override
    public Student findStudentInfoByAccount(String userName) {
        return studentMapper.findStudentInfoByAccount(userName);
    }

    @Override
    public Student findStudentInfoById(int userId) {
        return studentMapper.findStudentInfoById(userId);
    }
}
