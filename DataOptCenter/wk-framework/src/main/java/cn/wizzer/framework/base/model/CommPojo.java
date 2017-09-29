package cn.wizzer.framework.base.model;

import cn.wizzer.framework.util.StringUtil;
import org.nutz.dao.entity.annotation.*;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 共享字段,免得每个Pojo类都加创建时间和生成时间
 */
public abstract class CommPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Prev(els = @EL("$me.uid()"))
    @Comment("创建用户")
    @ColDefine(type = ColType.VARCHAR)
    protected String createUser;
    
    @Prev(els = @EL("$me.now()"))
    @Comment("创建时间")
    protected Date createTime;
    
    @Prev(els = @EL("$me.uname()"))
    @Comment("创建用户名称")
    @ColDefine(type = ColType.VARCHAR)
    protected String createUserName;
    
    @Prev(els = @EL("$me.now()"))
    @Comment("更新时间")
    protected Date updateTime;
    
    @Prev(els = @EL("$me.uid()"))
    @Comment("更新用户")
    @ColDefine(type = ColType.VARCHAR)
    protected String updateUser;
    
    @Prev(els = @EL("$me.uname()"))
    @Comment("更新用户名称")
    @ColDefine(type = ColType.VARCHAR)
    protected String updateUserName;
    
    @Column
    @Comment("删除标记")
    @Prev(els = @EL("$me.flag()"))
    @ColDefine(type = ColType.BOOLEAN)
    private Boolean delFlag;

    public String toString() {
        return String.format("/*%s*/%s", super.toString(), Json.toJson(this, JsonFormat.compact()));
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date now() {
        return new Date();
    }

    public String uid() {
        return StringUtil.getUid();
    }

    public String uname() {
        return StringUtil.getUsername();
    }
    
    public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
    
}
