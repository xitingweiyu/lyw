package com.wonders.bigdata.manageplatform.utils;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.AuthorityService;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.RoleAuthorityService;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统启动的时候将角色对应的actions写入authority.properties文件中
 * 文件的格式：角色id=actions
 * @author hsw
 *
 */
@Service
@Scope("singleton")
public class  AuthorityProperity implements ApplicationListener<ApplicationEvent>   {

	@Autowired
	private RoleAuthorityService roleAuthorityService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthorityService authorityService;
	
	public static Map<String, String> actionMap = new HashMap<String, String>();
	
	public void onApplicationEvent(ApplicationEvent arg0) {
		
		/*if(arg0.toString().contains("root")){
			
			//得到所有角色
			Page<RolePO> rolePage = new Page<RolePO>();
			QueryParam query = new QueryParam();
			Map<String, Object> eq = new HashMap<String, Object>();
			eq.put("deleteFlag", new Integer(Constant.USEANALYSE_NOT_DELETE));
			query.setEq(eq);
			rolePage.setParam(query);
			rolePage.setPagesize(-1);
			Page<RolePO> roles = roleService.getRolePage(rolePage);
			List<RolePO> rolePOs = roles.getResult();
			if(rolePOs!=null&&rolePOs.size()>0){
				//Properties propertie = new Properties();  
				Map<String, String> propertie = new HashMap<String, String>();
				for(RolePO rolePO:rolePOs){
					long roleId = rolePO.getId();
					//查询到角色对应的所有权限
					List<RoleAuthorityPO> roleAuthorityPOs = roleAuthorityService.getByRoleId(roleId);
					String actions = "";
					if(roleAuthorityPOs==null||roleAuthorityPOs.size()==0)
						continue;
					int i=0;
					for(RoleAuthorityPO roleAuthorityPO:roleAuthorityPOs){
						AuthorityPO authorityPO = authorityService.get(roleAuthorityPO.getAuthorityId());
						if(authorityPO==null)
							continue;
						if(authorityPO.getContainAuthorityIds()==null||authorityPO.getContainAuthorityIds().trim().equals("")){//权限的情况
							if(i==0){
								actions = actions + authorityPO.getActions();
							}else{
								actions = actions + "," + authorityPO.getActions();
							}
							i++;
						}else{//权限组的情况
							String chIds = doAuthorityIds(authorityPO.getContainAuthorityIds());
							String[] chidArray = chIds.split(",");
							for(int c=0;c<chidArray.length;c++){
								String chid = chidArray[c];
								AuthorityPO chaAuthorityPO = authorityService.get(Long.parseLong(chid));
								if(chaAuthorityPO==null)
									continue;
								
								if(i==0){
									actions = actions + authorityPO.getActions();
								}else{
									actions = actions + "," + authorityPO.getActions();
								}
								i++;
							}
						}
						
					}
					propertie.put(roleId+"", actions);
				}
				//PropertyTool.saveProperty(propertie);
				actionMap = propertie;
			}
			
		}*/
    }
	
	/**
	 * 组织权限ids
	 * @param authorityGroups
	 * @return
	 */
//	private String doAuthorityIds(String authorityGroups){
//		String authorityIds = "";
//		
//		String[] authortityGroupArray = authorityGroups.split(",");
//		for(int a=0;a<authortityGroupArray.length;a++){
//			String authorityGroupId = authortityGroupArray[a];
//			if(authorityGroupId.contains("(")){
//				if(a==0){
//					authorityIds = authorityIds + authorityGroupId.substring(authorityGroupId.indexOf("(")+1,authorityGroupId.indexOf(")"));
//				}else{
//					authorityIds = authorityIds + "," + authorityGroupId.substring(authorityGroupId.indexOf("(")+1,authorityGroupId.indexOf(")"));
//				}
//				
//			}else{
//				if(a==0){
//					authorityIds = authorityIds + authorityGroupId;
//				}else{
//					authorityIds = authorityIds + "," + authorityGroupId;
//				}
//			}
//			
//		}
//		
//		authorityIds = authorityIds.replace(";", ",");
//		
//		return authorityIds;
//	}
}
