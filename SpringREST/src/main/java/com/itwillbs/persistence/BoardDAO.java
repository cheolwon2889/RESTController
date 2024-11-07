package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	
	public void createBoard(BoardVO vo) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	public List<BoardVO> listPage(int page) throws Exception;
	
	public BoardVO getBoard(int bno) throws Exception;
	
	public void updateViewcnt(int bno) throws Exception;
	
	public void updateBoard(BoardVO vo) throws Exception; 
	
	public int deleteBoard(int bno) throws Exception;
	
	public int getTotalCount() throws Exception;
	
}