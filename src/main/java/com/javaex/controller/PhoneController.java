package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller   //import도 시켜줘야함 , 어너테이션? 주석의 개념
@RequestMapping(value="/phone")
public class PhoneController {
	
	//필드
	
	//생성자
	
	//메소드g/s
	
	/**메소드 일반**메소드마다 기능 1개씩 --> 기능마다url 부여**/
	/**
	 * 테스트
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET ,RequestMethod.POST})
	public String writeForm() {
		//로직구현
		System.out.println("writeForm");
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	@RequestMapping(value="/list" , method= {RequestMethod.GET , RequestMethod.POST})
	public String list() {
		//로직구현
		System.out.println("list");
		return "/WEB-INF/views/list.jsp";
	}
	**/
		//리스트
		@RequestMapping(value="/list", method= {RequestMethod.GET ,RequestMethod.POST})
		public String list(Model model){ //Model도 import
			
			System.out.println("list");
			
			//
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();
			System.out.println(personList.toString());
			
			
			//model --> date를 보내는 방법 --> 담아놓으면 된다.
			model.addAttribute("pList", personList);
			
			return "/WEB-INF/views/list.jsp";
		}
		//등록폼
		@RequestMapping(value="/writeForm", method= {RequestMethod.GET ,RequestMethod.POST})
		public String writeForm() {
			
			System.out.println("writeForm");	
			return "/WEB-INF/views/writeForm.jsp";
		}
	
		//등록
		//http://localhost:8088/phonebook3/phone/write?name=김이슬&hp=010-1111-1111&company=02-1111-1111
		@RequestMapping(value="write", method={RequestMethod.GET ,RequestMethod.POST})
		public String write(@RequestParam("name") String name,
							@RequestParam("hp") String hp,
							@RequestParam("company") String company) {
			System.out.println("write :"+ name + "," + hp + "," + company );
			
			PersonVo personVo = new PersonVo(name,hp,company);
			System.out.println(personVo.toString());
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personInsert(personVo); //personVo 를 db안에 저장해줌 

			return "redirect:/phone/list";
		}

		//수정폼 -->modifyForm		
		@RequestMapping(value="/modifyForm", method= {RequestMethod.GET ,RequestMethod.POST})
		public String modifyForm(Model model,
								@RequestParam("id") int id) {  //이렇게 써도 되는지? 
			
			System.out.println("modifyForm");	
			System.out.println(id);
			
			PhoneDao phoneDao = new PhoneDao();
			//id로 한사람의 정보 불러오기
			PersonVo personVo = phoneDao.getPerson(id);
			
			System.out.println("수정할 사람의 정보 : "+personVo);
			
			//date전달.
			model.addAttribute("oneVo", personVo);
			
			System.out.println("modifyForm");	
			return "/WEB-INF/views/modifyForm.jsp";
		}
			
		//수정 -->modify
		@RequestMapping(value="/modify", method= {RequestMethod.GET ,RequestMethod.POST})
		public String modify(@RequestParam("name") String name,
					         @RequestParam("hp") String hp,
						     @RequestParam("company") String company,
						     @RequestParam("id") int id) {			
			
			System.out.println("modify : "+name + "," + hp + "," + company + "," + id );
			
			PersonVo personVo = new PersonVo(id,name,hp,company);
			System.out.println(personVo.toString());
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personUpdate(personVo); //정보 수정 

			return "redirect:/phone/list";			
		}
		
		//삭제 -->delete
		@RequestMapping(value="/delete", method= {RequestMethod.GET ,RequestMethod.POST})
		public String delete(@RequestParam("id") int id) {			
			
			System.out.println("delete : "+ id );
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(id); //정보 수정 

			return "redirect:/phone/list";			
		}	
	
	//메소드 단위로 기능을 정의
	//이전에는 doGet() 메소드 1개에 파라미터로 구분
	
	
	
	
	
	
}
