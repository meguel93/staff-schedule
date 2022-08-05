package com.staff.schedule.app.staffschedule.service;

import com.staff.schedule.app.staffschedule.entity.User;
import com.staff.schedule.app.staffschedule.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
}
