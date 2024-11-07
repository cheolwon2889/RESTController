package com.itwillbs.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;



//@RestController : 모든 매핑된 매서드가 ResponseBody을 포함하고 있다.
@RestController
@RequestMapping("/rest")
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	/* @ResponseBody */
	@RequestMapping(value="/test1" , method = RequestMethod.GET)
	public /* @ResponseBody */ void test1() {
		logger.debug(" test1() 실행 ");
	}
	
	@RequestMapping( value = "/test2" , method = RequestMethod.GET )
	public String test2()  {
		logger.debug(" test2() 실행 ");
		// jackson-databind라이브러리가
		// 리턴하는 데이터를 JSON 형태로 변환
		return "222";
	}
	
	@RequestMapping( value = "/test3" , method = RequestMethod.GET )
	public double test3()  {
		logger.debug(" test3() 실행 ");
		// jackson-databind라이브러리가
		// 리턴하는 데이터를 JSON 형태로 변환
		return 1000.0;
	}
	
	@RequestMapping( value = "/test4" , method = RequestMethod.GET )
	public BoardVO test4()  {
		// json 표현 가능
		// 문자열, 숫자(정수, 실수) boolean(true,false), null, 객체{ }, 배열 [ ]
		logger.debug(" test4() 실행 ");
		
		BoardVO vo = new BoardVO();
		vo.setBno(100);
		vo.setTitle("테스트");
		vo.setContent("내용1234");
		vo.setWriter("작성자 1");
		
		
		return vo;
	}
	@RequestMapping( value = "/test5" , method = RequestMethod.GET )
	public ArrayList<BoardVO> test5()  {
		// json 표현 가능
		// 문자열, 숫자(정수, 실수) boolean(true,false), null, 객체{ }, 배열 [ ]
		logger.debug(" test5() 실행 ");
		
		ArrayList<BoardVO> boardList = new ArrayList<BoardVO>();
		for (int i = 0; i < 5; i++) {
			BoardVO vo = new BoardVO();
			vo.setBno(100+i);
			vo.setTitle("테스트" +i);
			vo.setContent("내용1234" +i);
			vo.setWriter("작성자 1" +i);
			
			boardList.add(vo);
		}
		
		
		return boardList;
	}
	@RequestMapping( value = "/test6" , method = RequestMethod.GET )
	public Map<Integer,BoardVO> test6()  {
		// json 표현 가능
		// 문자열, 숫자(정수, 실수) boolean(true,false), null, 객체{ }, 배열 [ ]
		logger.debug(" test6() 실행 ");
		
		Map<Integer, BoardVO> boardMap = new HashMap<Integer, BoardVO>();
		for (int i = 0; i < 5; i++) {
			BoardVO vo = new BoardVO();
			vo.setBno(100);
			vo.setTitle("테스트" +i);
			vo.setContent("내용1234" +i);
			vo.setWriter("작성자 1" +i);
			
			boardMap.put(i,vo);
		}
		
		
		
		return boardMap;
	}
	// http://localhost:8088/rest/test7?number=100
	// http://localhost:8088/rest/test7/100
	// @RequestMapping( value = "/test7" , method = RequestMethod.GET )
	@RequestMapping( value = "/test7/{num}" , method = RequestMethod.GET )
	public String test7( @PathVariable("num") Integer num /* @ModelAttribute("number")int number */)  {
		// 설계상 참조형 타입을 사용하는 것을 권장함. 
		logger.debug(" test7() 실행 ");
		logger.debug(" number : " + num);
		
		
		return "";
	}
	
	// 주소 호출을 통해서 데이터를 생성하는데. + 상태 정보까지 같이 보내보겠다.
	@RequestMapping( value = "/test8" , method = RequestMethod.GET )
	public ResponseEntity<Void> test8()  {
		// 단점 : 상태정보를 알 수 없다는 거.
		logger.debug(" test8() 실행 ");
		
		// ResponseEntity<T>
		// REST의 단점은 데이터만 있는건데 그걸 보완하기 위해 
		//  => 결과의 데이터 + HTTP 상태코드를 포함하는 객체 
		
		/*
		 * if(true) { // return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		 * 
		 * } else { // return new ResponseEntity<Void>(HttpStatus.OK); }
		 * 
		 * try { // return new ResponseEntity<Void>(HttpStatus.OK); } catch() { //
		 * return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); }
		 */
		
		
		logger.debug(" 왜지 ? "+ new ResponseEntity<Void>(HttpStatus.OK));
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Http 상태코드 
	 * 
	 * 100번대 : 처리중인 데이터의 상태 2
	 * 100 : 데이터의 일부를 서버가 받은 상태
	 * 
	 * 200번대 : 정상적인 응답  3
	 * 200 : 에러없이 정상 처리
	 * 201 : 요청이 성공적이었으며, 그결과로 새로운 리소스가 생성되었습니다.
	 * 
	 * 300번대 : 다른 URL을 처리 (리다이렉트시 문제)
	 * 301 : 요청한 페이지가 새 URL으로 변경
	 * 304 : 기존의 데이터와 변경이 없는경우 1
	 * 
	 * 400번대 : 서버에서 인식할 수 없음
	 * 400 : 요청에 문제가 있는 경우(데이터를 잘못 전달하는 경우)
	 * 403 : 서버에서 하락X(권한)
	 * 404 : URL에 해당하는 데이터가 없음.
	 * 405 : HTTP메서드 지원X
	 * 
	 * 500번대 : 서버 내부에러
	 * 500 : 서버가 데이터 처리시 에러 발생
	 * 502 : 게이트웨이나 프록시상태 문제 (과부하)
	 * 503 : 일시적 과부하/ 서비스 중단 상태
	 * 504 : 처리시간을 지난 요청(처리 불가능)
	 *  
	 * 
	 */
	// http://localhost:8088/rest/test9
	@RequestMapping( value = "/test9" , method = RequestMethod.GET )
	//public ResponseEntity<String> test9()  {
	public ResponseEntity<List<BoardVO>> test9()  {
		logger.debug(" test9() 실행 ");
		
		
		// return new ResponseEntity<String>("Result",HttpStatus.OK);
		// return new ResponseEntity<String>("Result",HttpStatus.NOT_FOUND);
		// return new ResponseEntity<String>("ErrData",HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity("ErrData",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/test10" , method = RequestMethod.GET)
	public ResponseEntity<String> test10() {
		logger.debug(" test10() 실행 ");
		
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", "text/html; charset=UTF-8 ");
		
		String tagData = "<script>";
		tagData += "alert('REST컨트롤러로 실행!'); ";
		tagData += "</script>";
		
		// return new ResponseEntity<String>("ErrData",HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<String>(tagData,respHeaders,HttpStatus.OK);
	}
}






















