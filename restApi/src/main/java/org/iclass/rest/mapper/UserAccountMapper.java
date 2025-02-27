package org.iclass.rest.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.rest.dto.UserAccount;

@Mapper
public interface UserAccountMapper {
		int insert(UserAccount dto);
		UserAccount selectForLogin(Map<String,String> map);
		int selectByEmail(String email);   //email 중복검사
		int selectByUserid(String userid);   //userid 중복검사
		int updateEmail(Map<String,String> map);	// email 수정
		int delete(String userid);
		UserAccount selectByPk(String userid);
}
