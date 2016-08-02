//package com.wonders.bigdata.manageplatform.service.user;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.wonders.bigdata.manageplatform.service.olduser.model.po.OldUserPO;
//import com.wonders.bigdata.manageplatform.service.olduser.service.OldUserService;
//import com.wonders.bud.framework.common.AbstractTestCase;
//
///**
// * <p>
// * Title: bud_[用户管理]_[模块名]
// * </p>
// * <p>
// * Description: [用户管理service测试]
// * </p>
// * 
// * @author Dy
// * @version $Revision$ 2014-10-24
// * @author (lastest modification by $Author$)
// * @since 20100901
// */
//public class UserServiceTest extends AbstractTestCase {
//
//	@Autowired
//	private OldUserService oldUserService;
//
//	@Test
//	public void testAreYouReady() {
//		Assert.assertNotNull(oldUserService);
//	}
//
//	@Test
//	public void testUser() {
//		String name = "xiaoming";
//		String name1 = "xiaoming2";
//		String login = "admin1";
//		OldUserPO userPO = new OldUserPO();
//		userPO.setEmail("");
//		userPO.setLogin(login);
//		userPO.setName(name);
//		userPO.setPhone("");
//		
//		//新增
//		oldUserService.save(userPO);
//		
//		Assert.assertNotNull("用户id不为空", userPO.getId());
//		
//		//重名
//		Map<String, Object> checker = new HashMap<String, Object>();
//		checker.put("name", name);
//		Assert.assertTrue("用户"+name+"存在", oldUserService.checkUser(null, checker));
//		
//		//修改
//		userPO.setName(name1);
//		oldUserService.update(userPO);
//		Assert.assertEquals("根据id获取到的更新后的用户名是："+name1, oldUserService.getById(userPO.getId()).getName(),name1);
//		
//		//根据login获取用户
//		Assert.assertEquals("根据login获取到的用户id是："+ userPO.getId(),oldUserService.getByLogin(login).getId(),userPO.getId());
//		
//		//list 查询
//		Assert.assertNotEquals("列表查询：用户数大于0", oldUserService.getList(null).size(), 0);
//		
//		//分页 查询
//		Assert.assertNotEquals("分页查询：用户数大于0", oldUserService.findByPage(null).getTotal(), 0);
//		
//		//删除
//		oldUserService.deleteById(userPO.getId());
//		Assert.assertNull("删除后查询不到改用户", oldUserService.getById(userPO.getId()));
//	}
//}
