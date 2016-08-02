package com.wonders.bigdata.manageplatform.service.resourcetype.model.po;



import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "bd_standard_catalog")
public class  StandardCatalogPO {
	//Field
	//主键
	private Long id;
	
	//父节点id
	private Long parent_id;
	
	//名称
	private String name;
	
	//编码
	private String decode;
	
	//类型
	private String type;
	
	//描述
	private String description;
	
	//创建时间
	private Date create_date;
	
	//删除位，0：正常，1：已删除
	private int delete_flag;
	
	//叶节点：y：叶节点 n:非叶节点 
	private String Isleafnode;
	
	
	/** default constructor */
	public StandardCatalogPO(){
	}

	/** full constructor */
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public StandardCatalogPO(Long id, Long parent_id, String name, String decode, String type, String description,
			Date create_date, int delete_flag, String isleafnode) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.name = name;
		this.decode = decode;
		this.type = type;
		this.description = description;
		this.create_date = create_date;
		this.delete_flag = delete_flag;
		this.Isleafnode = isleafnode;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "parent_id")
	public Long getParent_id() {
		return parent_id;
	}	

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "decode")
	public String getDecode() {
		return decode;
	}

	public void setDecode(String decode) {
		this.decode = decode;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "create_date")
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	@Column(name = "delete_flag")
	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	@Column(name="Isleafnode")
	public String getIsleafnode() {
		return Isleafnode;
	}

	public void setIsleafnode(String isleafnode) {
		Isleafnode = isleafnode;
	}
	
	
}
