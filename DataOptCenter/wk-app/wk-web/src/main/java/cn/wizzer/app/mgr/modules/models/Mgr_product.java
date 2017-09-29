package cn.wizzer.app.mgr.modules.models;

import org.nutz.dao.entity.annotation.*;

import cn.wizzer.framework.base.model.CommPojo;

import java.io.Serializable;

/**
 * <p>设备信息管理  实体类</p>
 * <p>模块：产品管理</p>
 * <p>日期：2017-09-18</p>
 * @version 1.0
 * @author xhguo
 */
@Table("mgr_product")
public class Mgr_product extends CommPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column
    @Name
    @Comment("ID")
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("产品型号")
    private String code;
    
    @Column
    @Comment("产品名称")
    private String name;
    
    @Column
    @Comment("产品功能")
    private String features;
    
    @Column
    @Comment("备注")
    private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
