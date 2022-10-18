package com.example.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.store.model.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	
	public String login(MemberDTO dto) {
		return sqlSession.selectOne("member.login", dto);
	}
	

	
	
	
	
}
