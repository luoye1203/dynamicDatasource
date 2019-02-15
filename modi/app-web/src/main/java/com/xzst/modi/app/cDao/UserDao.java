package com.xzst.modi.app.cDao;
import com.xzst.modi.app.dModel.user.User;
import com.xzst.modi.app.dModel.user.UserConfig;
import com.xzst.modi.app.dModel.user.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

	User findByUsername(final String username);
	/**
	 * 根据用户编号 即 yhbh查找用户的配置信息
	 * @param yhbh
	 * @return
	 */
	public UserConfig findUserConigByYhbh(String yhbh);

	/**
	 * 根据用户编号查找用户信息
	 * @param yhbh
	 * @return
	 */
	List<UserInfo> findUserInfoByYhbh(String yhbh);



	Map<String,String> findUserByYhbh(String yhbh);

	void addUserConfig(UserConfig userConfig);

	/**
	 * 获取系统配置
	 * @param pid
	 * @return
	 */
	public List queryTmSysParam(@Param("pid") final String pid);
}
