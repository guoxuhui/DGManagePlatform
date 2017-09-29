package cn.wizzer.app.mgr.modules.models;

import org.nutz.dao.entity.annotation.*;

import cn.wizzer.framework.base.model.CommPojo;

import java.io.Serializable;

/**
 * <p>设备信息管理  实体类</p>
 * <p>模块：客户管理</p>
 * <p>日期：2017-09-18</p>
 * @version 1.0
 * @author xhguo
 */
@Table("mgr_customer")
public class Mgr_customer extends CommPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 客户ID
     */
    @Column
    @Name
    @Comment("ID")
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("客户名称")
    private String name;
    
    @Column
    @Comment("联系人员")
    private String linkName;
    
    @Column
    @Comment("联系电话")
    private String linkPhone;
    
    @Column
    @Comment("联系地址")
    private String linkAddress;
    
    @Column
    @Comment("备注")
    private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
