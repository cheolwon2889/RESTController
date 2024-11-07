package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	// BoardDAO 객체 주입
	@Inject
	private BoardDAO bdao;
	
	private static final Logger logger 
		= LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Override
	public void regist(BoardVO vo) throws Exception {
		logger.debug(" regist(BoardVO vo) 호출  ");
		logger.debug(" DAO 객체의 해당 메서드 호출 ");
		bdao.createBoard(vo);
		
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		logger.debug(" listAll() 호출 ");
		logger.debug(" DAO의 글 리스트 조회하는 메서드 호출 ");
		return bdao.listAll();
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		logger.debug(" read(int bno) 호출 ");		
		return bdao.getBoard(bno);
	}

	@Override
	public void updateViewcnt(int bno) throws Exception {
		logger.debug(" updateViewcnt(int bno) 호출 ");
		bdao.updateViewcnt(bno);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		logger.debug(" modify(BoardVO vo) 호출  ");
		bdao.updateBoard(vo);
	}

	@Override
	public int remove(int bno) throws Exception {
		logger.debug(" remove(int bno)  호출 ");
		logger.debug(" bno : "+bno);
		return bdao.deleteBoard(bno);
	}


	@Override
	public int getTotalCount() throws Exception {
		logger.debug(" getTotalCount() 호출 ");
		return bdao.getTotalCount();
	}
	
	
	
	
	

}