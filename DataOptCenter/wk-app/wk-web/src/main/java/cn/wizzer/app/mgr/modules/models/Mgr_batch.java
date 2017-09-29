package cn.wizzer.app.mgr.modules.models;

import cn.wizzer.framework.base.model.CommPojo;
import cn.wizzer.framework.util.DateUtil;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.Calendar;

/**
 * <p>设备信息管理  实体类</p>
 * <p>模块：销售管理</p>
 * <p>日期：2017-09-18</p>
 * @version 1.0
 * @author xhguo
 */
@Table("mgr_device")
public class Mgr_batch extends CommPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 批次编号按照日期时间格式确定
     */
    @Column
    @Name
    @Prev(els = @EL("$me.getBatchId()"))
    @Comment("批次编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String id;
    
    @Column
    @Comment("客户编号")
    private String customerId;
    
    @Column
    @Comment("客户名称")
    private String customerName;
    
    @Column
    @Comment("设备类型编号")
    private String deviceTypeId;
    
    @Column
    @Comment("设备类型名称")
    private String deviceTypeName;
    
    @Column
    @Comment("软件版本")
    private String softVersion;
    
    @Column
    @Comment("销售备注")
    private String remark;
    
    public String getBatchId() {
        return DateUtil.format(Calendar.getInstance().getTime(), "yyyyMMddHHmmss");
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
