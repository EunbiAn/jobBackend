package egovframework.job.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.ApplyDTO;

import egovframework.job.vo.ApplyVO;

@Repository
public class ApplyDAO {


	@Autowired
	private SqlSession sqlSession;
	
	public List<ApplyVO> selectApplyList() {
		List<ApplyVO> applyVO = sqlSession.selectList("egovframework.mapper.job.ApplyMapper.selectApplyList");
		
		return applyVO;
	}

	 public ApplyVO selectApplyById(Long id) {
		 ApplyVO vo = sqlSession.selectOne("egovframework.mapper.job.ApplyMapper.selectApplyById", id);
	     return vo;
	 }

	//지원 등록
	public int createApply(ApplyDTO dto) {
	    return sqlSession.insert("egovframework.mapper.job.ApplyMapper.createApply",dto );
	}

	// 지원자별 지원내역 상태별 목록 조회
	public List<ApplyVO> selectApplyListByMemberAndState(long m_num, String state) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("m_num", m_num);
		map.put("state", state);

		List<ApplyVO> applyVO = sqlSession.selectList("egovframework.mapper.job.ApplyMapper.selectApplyListByMemberAndState",map);
		return applyVO;
	}

	 //지원자별 지원상태별 개수 조회
	 public  List<Map<String, Object>> countApplyState(long m_num) {
			return sqlSession.selectList("egovframework.mapper.job.ApplyMapper.countApplyState",m_num );
	 }
	 
	 // 지원자의 전체 지원내역 개수 조회
	 public int countApplyByMember(long m_num) {
		 return sqlSession.selectOne("egovframework.mapper.job.ApplyMapper.countApplyByMember",m_num );
	 }

	// 지원자별 지원내역 상태별 목록 조회
	public int countApplyStateByMember(long m_num, String state) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("m_num", m_num);
		map.put("state", state);

		int resultCnt = sqlSession.selectOne("egovframework.mapper.job.ApplyMapper.countApplyStateByMember",map);
		return resultCnt;
	}
	 
	 //지원 취소	    
	public int deleteApply(long a_id) {
		 return sqlSession.delete("egovframework.mapper.job.ApplyMapper.deleteApply",a_id );
	}
	
	// 지원 상태 변경
	public int updateApplyState(ApplyDTO dto) {
		return sqlSession.update("egovframework.mapper.job.ApplyMapper.updateApply", dto);
	}
	
}
