package com.example.CCV.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.CCV.DTO.AdminDTO;
import com.example.CCV.DTO.CinemaDTO;
import com.example.CCV.DTO.CinemaimgDTO;
//import com.example.CCV.DTO.ApplicationcinemastatusDTO;
//import com.example.CCV.DTO.CinemaimgDTO;
import com.example.CCV.DTO.MovieDTO;
import com.example.CCV.DTO.PostDTO;
import com.example.CCV.DTO.ScreenDTO;
import com.example.CCV.DTO.TrailerDTO;
import com.example.CCV.Entity.Admin;
import com.example.CCV.Entity.Cinema;
//import com.example.CCV.Entity.ApplicationCinemaStatus;
import com.example.CCV.Entity.Movie;
import com.example.CCV.Entity.Screen;
import com.example.CCV.Model.SaveCinemaimg;
import com.example.CCV.Repository.AdminRepository;

import com.example.CCV.Service.AdminService;
import com.example.CCV.Service.CinemaService;
import com.example.CCV.Service.MovieService;
import com.example.CCV.Service.ScreenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import oracle.jdbc.driver.parser.util.Array;

@Controller
public class AdminController {
	@Value("${project.upload.path}")
	private String bassPath;

	@Autowired
	AdminService adminService;

	@Autowired
	MovieService movieService;

	@Autowired
	CinemaService cinemaService;

	@Autowired
	ScreenService screenService;

	// 1) 로그인
	@PostMapping("/adminLogin")
	public String adminlogin(AdminDTO dto, HttpServletRequest request) {
		String id = dto.getId();
		String pwd = dto.getPwd();
		Admin admin = adminService.adminLogin(id, pwd);
		HttpSession session = request.getSession();
		if (admin != null) {
			session.setAttribute("adminId", admin.getId());
			return "/admin/adminPage";
		} else {
			return "redirect:/admin";
		}
	}

	// 2) 로그아웃
	@GetMapping("/adminlogout")
	public String adminlogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("adminId");
		session.removeAttribute("adminType");
		return "redirect:/admin";

	}

	// 3) 메인화면으로 돌아가기
	@GetMapping("/gotoMain")
	public String gotoMain(Model model) {
		model.addAttribute("req", null);
		return "/admin/adminPage";
	}

	// 4) 영화 등록폼
	@GetMapping("/insertMovieForm")
	public String insertMovieForm(Model model) {
		model.addAttribute("req", "/admin/insertMovieForm");
		return "/admin/adminPage";
	}

	// 5) 영화 등록
	@PostMapping("/insertMovie")
	public String insertMovie(Model model, MovieDTO dto, @RequestParam("img") List<MultipartFile> pl,
			@RequestParam("vo") List<MultipartFile> tl, HttpServletRequest request) {
		List<PostDTO> plist = new ArrayList<>();
		for (int i = 0; i < pl.size(); i++) {
			String postName = pl.get(i).getOriginalFilename();
			if (!postName.equals("")) {
				PostDTO pDTO = new PostDTO();
				pDTO.setPostname(postName);
				plist.add(pDTO);
			}
		}
		dto.setPost(plist);

		List<TrailerDTO> tlist = new ArrayList<>();
		for (int i = 0; i < tl.size(); i++) {
			String trailerName = tl.get(i).getOriginalFilename();
			if (!trailerName.equals("")) {
				TrailerDTO tDTO = new TrailerDTO();
				tDTO.setTrailername(trailerName);
				tlist.add(tDTO);
			}
		}
		dto.setTrailer(tlist);
		// System.out.println(dto.toString());
		int houre = Integer.parseInt(request.getParameter("h")) * 60;
		int minute = Integer.parseInt(request.getParameter("m"));
		int runningtime = houre + minute;
		dto.setRunningtime(runningtime);
		System.out.println(dto);
		Movie movie = movieService.insertMovie(dto);
		// System.out.println(movie);
		return "/admin/adminPage";
	}

	// 6) 영화관 등록폼
	@GetMapping("/insertCinemaForm")
	public String inserCinemaForm(Model model) {
		model.addAttribute("req", "/admin/insertCinemaForm");
		return "/admin/adminPage";
	}

	// 7) 영화관 등록 중간
	@PostMapping("/insertCinema")
	public String insertCinema(Model model, CinemaDTO dto, @RequestParam("img") List<MultipartFile> list) {
		List<CinemaimgDTO> imgs = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String imgName = list.get(i).getOriginalFilename();
			if (!imgName.equals("")) {
				CinemaimgDTO cinemaimgDTO = new CinemaimgDTO();
				cinemaimgDTO.setImgname(imgName);
				imgs.add(cinemaimgDTO);

			}
		}
		dto.setCinemaimg(imgs);
		model.addAttribute("dto", dto);
		model.addAttribute("size", list.size() - 1);
		model.addAttribute("req", "/admin/detailScreen");
		return "/admin/adminPage";
	}

	// 8) 상영관 상세 설정
	@PostMapping("/detail")
	public String test(Model model, HttpServletRequest request, @RequestParam("location") List<String> locations,
			@RequestParam("screenname") List<String> screennames, @RequestParam("seat") List<Integer> seats,
			@RequestParam("screen_type") List<String> types, @RequestParam("imgname") List<String> imgnames) {
		CinemaDTO dto = new CinemaDTO();
		dto.setArea(request.getParameter("area"));
		dto.setLocation(request.getParameter("location"));
		dto.setManager(request.getParameter("manager"));
		dto.setTotal(Integer.parseInt(request.getParameter("total")));
		dto.setOpening(Date.valueOf(request.getParameter("opening")));

		List<CinemaimgDTO> imgs = new ArrayList<>();
		int size = Integer.parseInt(request.getParameter("size"));
		for (int i = 0; i <= size; i++) {
			if (!imgnames.get(i).equals("")) {
				CinemaimgDTO cinemaimgDTO = new CinemaimgDTO();
				cinemaimgDTO.setImgname(imgnames.get(i));
				imgs.add(cinemaimgDTO);
			}
			dto.setCinemaimg(imgs);
		}
		List<ScreenDTO> screens = new ArrayList<>();
		for (int i = 0; i < locations.size(); i++) {
			ScreenDTO screenDTO = new ScreenDTO();
			screenDTO.setLocation(locations.get(i));
			screenDTO.setScreenname(screennames.get(i));
			screenDTO.setSeat(seats.get(i));
			screenDTO.setScreen_type(types.get(i));
			screens.add(screenDTO);
		}
		dto.setScreen(screens);
		boolean result = false;
		Cinema cinema = cinemaService.insertCinema(dto);
		if (cinema != null)
			result = true;
		System.out.println(result);
		if (!result) {
			model.addAttribute("req", "/admin/errorPage");
		}
		return "/admin/adminPage";
	}

	// 9) 상영일정 등록
	@GetMapping("/insertShowingForm")
	public String inserShowingForm(Model model) {
		try {
			model.addAttribute("req", "/admin/insertShowingForm");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			List<String> titles = movieService.titles();
//			System.out.println(titles);
			// 영화별 개봉일자 묶기
			Map<String, String> titleToopenDate = new LinkedHashMap<>();
			for (int i = 0; i < titles.size(); i++) {
				java.util.Date open = movieService.findOpening(titles.get(i));
				String openDate = formatter.format(open) + "T00:00";
				// System.out.println(openDate);
				titleToopenDate.put(titles.get(i), openDate);
			}

			List<Integer> runningtimes = movieService.runningtimes();
			Map<String, List<Integer>> titleToRunningTime = new LinkedHashMap<>();
			for (int i = 0; i < runningtimes.size(); i++) {
				int h = runningtimes.get(i) / 60;
				int m = runningtimes.get(i) % 60;
				titleToRunningTime.put(titles.get(i), Arrays.asList(h, m));
			}
//			System.out.println(titleToRunningTime);

//			System.out.println(titleToopenDate);

			// 지역별 지점 묶기
			Set<String> area = cinemaService.areas();
			List<String> areas = new ArrayList<>(area);

			Map<String, List<String>> areaTolocation = new LinkedHashMap<>();
			for (int i = 0; i < areas.size(); i++) {
				List<String> locations = cinemaService.locations(areas.get(i));
				areaTolocation.put(areas.get(i), locations);
			}
//			System.out.println(areaTolocation);

			// 지점별 상영관이름 묶기
			// 1 모든 지점명 가져오기
			List<String> allLocation = cinemaService.allLocation();
			Map<String, List<String>> locationToscreenName = new LinkedHashMap<>();
			for (int i = 0; i < allLocation.size(); i++) {
				List<String> screenNames = screenService.screenNames(allLocation.get(i));
				locationToscreenName.put(allLocation.get(i), screenNames);
			}
//			System.out.println(locationToscreenName);

			model.addAttribute("titleToopenDate", titleToopenDate);
			model.addAttribute("areaTolocation", areaTolocation);
			model.addAttribute("locationToscreenName", locationToscreenName);
			model.addAttribute("titles", titles);
			model.addAttribute("titleToRunningTime",titleToRunningTime);

		} catch (Exception e) {
			model.addAttribute("req", "/admin/errorPage");
		}

		return "/admin/adminPage";
	}

}
