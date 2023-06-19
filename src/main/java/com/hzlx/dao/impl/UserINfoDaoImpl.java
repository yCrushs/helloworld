package com.hzlx.dao.impl;

import com.hzlx.dao.UserInfoDao;
import com.hzlx.entity.UserInfo;
import com.hzlx.utils.BaseDao;
import com.mysql.cj.jdbc.Driver;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserINfoDaoImpl extends BaseDao<UserInfo> implements UserInfoDao {
    @Override
    public List<UserInfo> getUserInfoAll(){
       /* String sql="select * from t_user_info";
        return selectListForObject(sql, UserInfo.class);*/

        String URL="jdbc:mysql://localhost:3306/bgms_db?useSSL=false&useUniCode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String USER="root";
        String PASSWOPRD="Lsl20030220.";
        String SQL="select * from t_user_info";

        List<UserInfo> userInfos=new ArrayList<UserInfo>();
        try {
            Driver driver=new Driver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWOPRD);
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                UserInfo userInfo=new UserInfo();
                userInfo.setId(resultSet.getInt(1));
                userInfo.setUserName(resultSet.getString(2));
                userInfo.setPassword(resultSet.getString(3));
                userInfo.setNickName(resultSet.getString(4));
                userInfo.setTel(resultSet.getString(5));
                userInfo.setAddress(resultSet.getString(6));
                userInfo.setSex(resultSet.getInt(7));
                userInfo.setAvatar(resultSet.getString(8));
                userInfo.setCreateTime(resultSet.getDate(9));
                userInfo.setStatus(resultSet.getInt(10));
                userInfos.add(userInfo);
            }
            return userInfos;
        } catch (SQLException e) {
            return null;
        }
    }
}
