package com.staff.schedule.app.staffschedule.dao;

import com.staff.schedule.app.staffschedule.entity.User;

public interface IUserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
