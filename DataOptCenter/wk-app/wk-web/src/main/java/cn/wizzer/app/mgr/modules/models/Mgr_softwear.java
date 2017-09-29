package cn.wizzer.app.mgr.modules.models;

import org.nutz.dao.entity.annotation.*;

import cn.wizzer.framework.base.model.CommPojo;

import java.io.Serializable;

/**
 * <p>设备信息管理  实体类</p>
 * <p>模块：软件管理</p>
 * <p>日期：2017-09-18</p>
 * @version 1.0
 * @author xhguo
 */
@Table("mgr_product")
public class Mgr_softwear extends CommPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column
    @Name
    @Comment("ID")
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("软件名称")
    private String name;
    
    @Column
    @Comment("所属客户ID")
    private String customerId;
    
    @Column
    @Comment("所属客户名称")
    private String customerName;
    
    @Column
    @Comment("所属产品ID")
    private String productId;
    
    @Column
    @Comment("所属产品名称")
    private String productName;
    
    @Column
    @Comment("当前软件版本")
    private String currVersion;
    
    @Column
    @Comment("软件发布时间")
    @Prev(els = @EL("$me.now()"))
    private String releaseTime;
    
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCurrVersion() {
		return currVersion;
	}

	public void setCurrVersion(String currVersion) {
		this.currVersion = currVersion;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
