package com.example.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.model.MemberDTO;

@Service
public class AdminDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	public String login(MemberDTO dto) {
		return sqlSession.selectOne("admin.login", dto);
		// admin.login -> 네임스페이스.아이디 
	}
	
	
	
	
	
	

}
