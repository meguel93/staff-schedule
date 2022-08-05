package com.staff.schedule.app.staffschedule.dao;


import com.staff.schedule.app.staffschedule.entity.Role;

public interface IRoleDao {

	public Role findRoleByName(String theRoleName);
	
}
