package com.example.CCV.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CCV.DTO.UserMemberDTO;
import com.example.CCV.Entity.UserMember;
import com.example.CCV.Model.RandomNumber;
import com.example.CCV.Model.ReadBannerImgList;
import com.example.CCV.Service.EmailService;
import com.example.CCV.Service.UserMemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	EmailService emailService;

	@Autowired
	UserMemberService userMemberService;

	// 1) 로그인 버튼 누를시 => 로그인 폼 호출
	@GetMapping("/login")
	public String loginForm() {
		System.out.println("1)로그인창 호출");
		return "/user/loginForm";
	}

	// 1-1) 로그인 탭 선택시
	@GetMapping("/memberLoginForm")
	public String memberLoginForm() {
		return "/user/memberLoginForm";
	}

	// 1-2) 비회원 예매 탭 선택시
	@GetMapping("/visterForm")
	public String visterForm() {
		return "/user/visterForm";
	}

	// 1-3) 비회원 예매확인 탭 선택시
	@GetMapping("/visterTicketCheck")
	public String visterTicketCheck() {
		return "/user/visterTicketCheck";
	}

	// 1-4) 로그인 탭 선택시 하단에 회원가입 창
	@GetMapping("/joinMemberBf")
	public String joinMemberBf() {
		return "/user/joinMemberBf";
	}

	// 2)회원가입 폼
	@GetMapping("/userCheck")
	public String userCheck() {
		System.out.println("2)회원 가입여부 체크");
		return "/user/userCheck";
	}

	// 3)가입여부 확인
	@PostMapping("/findMember")
	public String findMember(HttpServletRequest request, Model model) {
		System.out.println("3)가입 여부 확인");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String tel_num = request.getParameter("tel-num");
		String byear = birthday.substring(0, 2);
		String bmonth = birthday.substring(2, 4);
		String bday = birthday.substring(4, birthday.length());
		String tel2 = null;
		String tel3 = null;
		String id = null;
		if (tel_num.length() == 8) {
			tel2 = tel_num.substring(0, tel_num.length() / 2);
			tel3 = tel_num.substring(tel_num.length() / 2);
		} else if (tel_num.length() == 7) {
			tel2 = tel_num.substring(0, 4);
			tel3 = tel_num.substring(4);
		}
		UserMember usermember = userMemberService.findUser(name, byear, bmonth, bday, tel2, tel3);
		// System.out.println(usermember);
		if (usermember != null) {
			id = usermember.getId();
			model.addAttribute("idlength", id.length());
		}
		model.addAttribute("name", name);
		model.addAttribute("byear", byear);
		model.addAttribute("bmonth", bmonth);
		model.addAttribute("bday", bday);
		model.addAttribute("tel2", tel2);
		model.addAttribute("tel3", tel3);
		model.addAttribute("usermember", usermember);
		model.addAttribute("name", name);
		model.addAttribute("id", id);
		if (birthday.substring(0, 1).equals("9")) {
			birthday = "19" + byear + bmonth + bday;
		} else {
			birthday = "20" + byear + bmonth + bday;
		}
		model.addAttribute("birthday", birthday);
		return "/user/joinMemberForm";

	}

	// 4) 본인인증
	@GetMapping("/identityVerification")
	public String identityVerification() {
		return "/user/identityVerification";
	}

	// 5) 이메일 인증
	@GetMapping("/identityByEmail")
	public String identityByEmail(Model model, HttpServletRequest request) {
		RandomNumber randomNumber = new RandomNumber();
		String code = randomNumber.codeNumber();
		System.out.println("전달받은 코드번호 : " + code);
		model.addAttribute("code", code);
		return "/user/identityByEmail";
	}

	// 6) 인증번호 보내기
	@PostMapping("/sendCodeByEmail")
	public String sendCodeByEmail(HttpServletRequest request, Model model) {
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String writeEmail = request.getParameter("wireteEmail");
		if (email2.equals("직접입력")) {
			email2 = writeEmail;
		}
		String code = request.getParameter("code");
		if (email2.equals("직접입력")) {
			email2 = request.getParameter("writeEmail");
		}
		String email = email1 + "@" + email2;
		System.out.println(email);
		String subject = "이메일 인증 코드";
		String text = "인증 코드 : " + code;
		boolean result = emailService.sendEmail(email, subject, text);

		return "/user/identityByEmail";
	}

	// 7) 약관동의
	@GetMapping("/agree")
	public String agree(HttpServletRequest request, Model model) {
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String writeEmail = request.getParameter("writeEmail");
		if (email2.equals("직접입력")) {
			email2 = writeEmail;
		}
		System.out.println(email1);
		System.out.println(email2);
		model.addAttribute("email1", email1);
		model.addAttribute("email2", email2);
		return "/user/agree";
	}

	// 8) 회원정보 입력
	@GetMapping("/userInfoWrite")
	public String userInfoWrite(HttpServletRequest request, Model model) {
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		System.out.println("전달받은 이메일1 : " + email1);
		System.out.println("전달받은 이메일2 : " + email2);
		model.addAttribute("email1", email1);
		model.addAttribute("email2", email2);
		return "/user/userInfoWrite";
	}

	// 9) id 중복확인
	@GetMapping("/idCheck")
	public String idCheck(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		UserMember usermember = userMemberService.idCheck(id);
		boolean result;
		if (usermember != null)
			result = false; // 해당 아이디에 대한 회원이 존재하면 false
		else
			result = true; // 존재하지 않으면 true
		System.out.println(result);
		model.addAttribute("result", result);
		model.addAttribute("id", id);
		return "/user/idCheck";
	}

	// 10) 회원가입
	@PostMapping("/insertUserInfo")
	public String inserUserInfo(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String byear = request.getParameter("byear");

		String bmonth = request.getParameter("bmonth");
		if (bmonth.length() == 1) {
			bmonth = "0" + bmonth;
		}
		String bday = request.getParameter("bday");
		if (bday.length() == 1) {
			bday = "0" + bday;
		}
		String birthday = byear + bmonth + bday;
		byear = byear.substring(2);

		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		Date logtime = new Date();

		UserMemberDTO dto = new UserMemberDTO(id, pwd, name, birthday, byear, bmonth, bday, postcode, address,
				detailAddress, email1, email2, tel1, tel2, tel3, logtime);
		System.out.println("전달받은 내용 : " + dto);
		UserMember saveUser = userMemberService.joinUserinfo(dto);
		System.out.println(saveUser);
		return "/user/successJoinMember";
	}

	// 로그인
	@PostMapping("/loginCheck")
	public String login(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		UserMember usermember = userMemberService.loginCheck(id, pwd);
		/*
		 * System.out.println(id); System.out.println(pwd);
		 * System.out.println(usermember); 데이터는 잘 받아온다.
		 */
		if (usermember != null) {
			// 로그인 성공시 id,name 세션으로 저장
			session.setAttribute("memId", usermember.getId());
			session.setAttribute("memName", usermember.getName());
			// return "main"시 동영상 재생을 시키기 위한 동영상 배열이 null값이 되는 버그가 발생하여 redirect로 요청을 새롭게
			// 보내도록 수정
			return "redirect:/";
		} else {
			model.addAttribute("id", id);
			return "/user/falseLogin";
		}
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("memId");
		session.removeAttribute("memName");
		// return "main"시 동영상 재생을 시키기 위한 동영상 배열이 null값이 되는 버그가 발생하여 redirect로 요청을 새롭게
		// 보내도록 수정
		return "redirect:/";
	}

	// 빠른예매
	@GetMapping("/tickecting")
	public String tickecting(Model model) {
		ReadBannerImgList bannerList = new ReadBannerImgList();
		List<String> bannerImgName = bannerList.bannerList();
		model.addAttribute("bannerImgName", bannerImgName);
		String webBannerPath = "/banner_img/";
		model.addAttribute("bannerPath", webBannerPath);
		return "/user/tickecting";
	}
}
