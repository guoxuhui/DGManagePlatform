package cn.wizzer.app.mgr.modules.models;

import org.nutz.dao.entity.annotation.*;

import cn.wizzer.framework.base.model.CommPojo;

import java.io.Serializable;

/**
 * <p>设备信息管理  实体类</p>
 * <p>模块：历史版本管理</p>
 * <p>日期：2017-09-18</p>
 * @version 1.0
 * @author xhguo
 */
@Table("mgr_product")
public class Mgr_softwear_version extends CommPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column
    @Name
    @Comment("ID")
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("软件版本")
    private String version;
    
    @Column
    @Comment("软件发布时间")
    @Prev(els = @EL("$me.now()"))
    private String releaseTime;
    
    @Column
    @Comment("备注")
    private String remark;
    
    @Column
    @Comment("所属软件")
    private String softwearId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public String getSoftwearId() {
		return softwearId;
	}

	public void setSoftwearId(String softwearId) {
		this.softwearId = softwearId;
	}
}
