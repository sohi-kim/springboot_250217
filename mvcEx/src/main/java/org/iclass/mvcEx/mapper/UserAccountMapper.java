package org.iclass.mvcEx.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvcEx.dto.UserAccount;

@Mapper
public interface UserAccountMapper {
	int insert(UserAccount account);
	UserAccount selectForLogin(Map<String,String> map);

}
