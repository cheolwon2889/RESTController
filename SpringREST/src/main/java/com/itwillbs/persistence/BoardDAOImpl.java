package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	// 디비연결정보를 처리하는 객체를 주입사용
	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger 
	   = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	private static final String NAMESPACE 
	                   ="com.itwillbs.mapper.BoardMapper.";
//	private static final String NAMESPACE_MEM 
//			="com.itwillbs.mapper.MemberMapper.";
	
	
	@Override
	public void createBoard(BoardVO vo) throws Exception {
		logger.debug(" createBoard(BoardVO vo) 호출 ");
		logger.debug(" 디비 연결 - SQL 호출 - 실행 ");
		sqlSession.insert(NAMESPACE + "insertBoard",vo);
	}


	@Override
	public List<BoardVO> listAll() throws Exception {
		logger.debug(" listAll() 호출 ");
		return sqlSession.selectList(NAMESPACE + "listAll");
	}


	@Override
	public BoardVO getBoard(int bno) throws Exception {
		logger.debug(" getBoard(int bno) 호출 ");
		return sqlSession.selectOne(NAMESPACE + "getBoard",bno);
	}


	@Override
	public void updateViewcnt(int bno) throws Exception {
		logger.debug(" updateViewcnt(int bno) 호출 ");
		// 조회수 1증가
		sqlSession.update(NAMESPACE + "increseViewcnt",bno);
	}


	@Override
	public void updateBoard(BoardVO vo) throws Exception {
		logger.debug(" updateBoard(BoardVO vo) 호출 ");
		sqlSession.update(NAMESPACE + "updateBoard",vo);
	}


	@Override
	public int deleteBoard(int bno) throws Exception {
		logger.debug(" deleteBoard(int bno) 호출 ");
		logger.debug("bno : "+bno);
		return sqlSession.delete(NAMESPACE + "deleteBoard", bno);
	}


	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		logger.debug(" listPage(int page) 호출 ");
		
		if(page <= 0) {
			page = 1;
		}
		// 1-0 / 2-10 / 3-20 / 4-30 ....
		// 페이지에 따른 시작 위치(인덱스)계산
		page = (page - 1) * 10;
		
		return sqlSession.selectList(NAMESPACE + "listPage",page);
	}




	@Override
	public int getTotalCount() throws Exception {
		logger.debug(" getTotalCount() 실행 ");
		return sqlSession.selectOne(NAMESPACE + "pageCnt");
	}
	
	
	
	
	

}