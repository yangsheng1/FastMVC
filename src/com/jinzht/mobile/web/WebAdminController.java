package com.jinzht.mobile.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.type.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.jinzht.tools.Config;
import com.jinzht.tools.FileUtil;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Banner;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Contentimages;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Weburlrecord;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.InvestorManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.manager.WebManager;
import com.jinzht.web.test.User;

@Controller
public class WebAdminController extends BaseController {
	@Autowired
	private WebManager webManager;
	@Autowired
	private SystemManager systemManger;
	
	@RequestMapping(value = "/admin/login")
	public String webEditor() {
		return "/admin/login";
	}
	
	
	@RequestMapping(value = "/admin/loginAction")
	public String loginAction() {
		return "/admin/index";
	}
	
	@RequestMapping(value = "/admin/dashboard")
	public String dashboard() {
		return "/admin/dashboard";
	}
	
	
	@RequestMapping(value = "/admin/adminJsp")
	public String adminJsp() {
		return "/admin/adminIndex";
	}
	
	/***
	 * top
	 * @return
	 */
	@RequestMapping(value = "/admin/top")
	public String topJSP() {
		return "/admin/top";
	}
	
	/***
	 * content
	 * @return
	 */
	@RequestMapping(value = "/admin/content")
	public String contentJSP() {
		return "/admin/content";
	}
	
	/***
	 * left
	 * @return
	 */
	@RequestMapping(value = "/admin/left")
	public String leftJSP() {
		return "/admin/left";
	}
	/***
	 * bottom
	 * @return
	 */
	@RequestMapping(value = "/admin/bottom")
	public String bottomJSP() {
		return "/admin/bottom";
	}
	
	
	
	/***
	 * banner列表
	 * @return
	 */
	@RequestMapping(value = "/admin/bannerListAdmin")
	public String bannerListAdmin(ModelMap map) {
		List<Banner> list = this.systemManger.findBannerInfoList();
		map.put("items", list.iterator());
		return "/admin/banner/bannerList";
	}
	
	
	/***
	 * banner列表
	 * @return
	 */
	@RequestMapping(value = "/admin/editBanner")
	public String editBanner(
			@RequestParam(value="bannerId",required=false)Integer bannerId,
			ModelMap map) {
		if(bannerId!=null)
		{
			Banner banner = this.systemManger.getBannerDao().findById(bannerId);
			map.put("banner", banner);
		}
		
		return "/admin/banner/editorBanner";
	}
	
	@RequestMapping(value = "/admin/uploadImage")
	@ResponseBody
	/***
	 * 上传图片
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map uploadImage(
			@RequestParam(value = "file",required = false) MultipartFile[] images,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		// 保存图片
		String fileName ="";
		String result="";
		if(images!=null && images.length>0){
			
			MultipartFile file = null;
			Set items = new HashSet();
			List list = new ArrayList();
			for(int i =0;i<images.length;i++){
				if (images[i]!= null) {
					file = images[i];
					fileName = String.format(
							Config.STRING_USER_FEELING_PICTUREA_FORMAT,
							new Date().getTime(),i);
					result = FileUtil.savePicture(
							file, fileName,
							"upload/uploadImages/");
					if (!result.equals("")) {
						fileName = Config.STRING_SYSTEM_ADDRESS
								+ "upload/uploadImages/" + result;
						list.add(fileName);
					} else {
						fileName = "";
					}
					
				}
			}
			
			session.setAttribute("images", list);
		}
			return getResult();
	}
	
	
	@RequestMapping(value = "/admin/addBanner")
	/***
	 * 添加Banner
	 * @return
	 */
	public String addBanner(
			@RequestParam(value = "bannerId",required = false) Integer bannerId,
			@RequestParam(value = "url",required = false) String url,
			@RequestParam(value = "name",required = false)String name,
			@RequestParam(value = "image",required = false) String image,
			@RequestParam(value = "description",required = false) String description,
			ModelMap map,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		
		List l = (List) session.getAttribute("images");
		Banner banner;
		
		if(bannerId!=-1)
		{
			banner = this.systemManger.getBannerDao().findById(bannerId);
		}else{
			banner = new Banner();
		}
		
		banner.setName(name);
		banner.setUrl(url);
		banner.setDescription(description);
		
		if(image!=null)
		{
			banner.setImage(image);
		}else{
			if(l!=null && l.size()>0)
			{
				banner.setImage(l.get(0).toString());
			}
		}
		
		if(bannerId!=-1)
		{
			this.systemManger.getBannerDao().saveOrUpdate(banner);
		}else{
			this.systemManger.getBannerDao().save(banner);
		}
		
		List<Banner> list = this.systemManger.findBannerInfoList();
		map.put("items", list.iterator());
		return "/admin/banner/bannerList";
	}
}
