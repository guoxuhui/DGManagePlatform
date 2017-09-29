package cn.wizzer.app.mgr.modules.models;

import cn.wizzer.framework.base.model.CommPojo;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * <p>设备信息管理  实体类</p>
 * <p>模块：设备管理</p>
 * <p>日期：2017-09-18</p>
 * @version 1.0
 * @author xhguo
 */
@Table("mgr_device")
public class Mgr_device extends CommPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 设备编号位IMEI号中间10位，不可编辑
     */
    @Column
    @Name
    @Comment("设备编号")
    private String id;

    @Column
    @Comment("IMEI号15位")
    private String imei;
    
    @Column
    @Comment("客户编号")
    private String customerId;
    
    @Column
    @Comment("客户名称")
    private String customerName;
    
    @Column
    @Comment("所属产品ID")
    private String productId;
    
    @Column
    @Comment("所属产品名称")
    private String productName;
    
    @Column
    @Comment("软件版本")
    private String softVersion;
    
    @Column
    @Comment("是否报废")
    private int isScrap;
    
    @Column
    @Comment("销售备注")
    private String remark;
    
    @Column
    @Comment("所属批次")
    private String batchId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
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

	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}

	public int getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(int isScrap) {
		this.isScrap = isScrap;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
    
}
