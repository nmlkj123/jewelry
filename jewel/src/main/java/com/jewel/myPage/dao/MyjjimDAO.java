package com.jewel.myPage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jewel.common.dao.AbstractDAO;

@Repository("myJJimDAO")
public class MyjjimDAO extends AbstractDAO {
	public List<Map<String, Object>> selectMyjjimList(Map<String, Object> map) {
		return(List<Map<String, Object>>) selectList("myPage.selectMyjjimList", map);
	}

	public Map<String, Object> selectMyInfo(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("myPage.selectMyInfo", map);
	}
}