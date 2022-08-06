package za.co.staffschedule.service;

import za.co.staffschedule.entity.User;
import za.co.staffschedule.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
}
