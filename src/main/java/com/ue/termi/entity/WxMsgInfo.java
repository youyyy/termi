package com.ue.termi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName wx_msg_info
 */
@TableName(value ="wx_msg_info")
@Data
public class WxMsgInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 快照
     */
    private String wxSnapshot;

    /**
     * ToUserName
     */
    private String toUserName;

    /**
     * FromUserName
     */
    private String fromUserName;

    /**
     * CreateTime
     */
    private String createTime;

    /**
     * MsgType
     */
    private String msgType;

    /**
     * Content
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createrTime;

    /**
     * 更新时间
     */
    private Date updaterTime;

    /**
     * 创建者id
     */
    private String createrId;

    /**
     * 修改者id
     */
    private String updaterId;

    /**
     * 数据删除标记
     */
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WxMsgInfo other = (WxMsgInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWxSnapshot() == null ? other.getWxSnapshot() == null : this.getWxSnapshot().equals(other.getWxSnapshot()))
            && (this.getToUserName() == null ? other.getToUserName() == null : this.getToUserName().equals(other.getToUserName()))
            && (this.getFromUserName() == null ? other.getFromUserName() == null : this.getFromUserName().equals(other.getFromUserName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getMsgType() == null ? other.getMsgType() == null : this.getMsgType().equals(other.getMsgType()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreaterTime() == null ? other.getCreaterTime() == null : this.getCreaterTime().equals(other.getCreaterTime()))
            && (this.getUpdaterTime() == null ? other.getUpdaterTime() == null : this.getUpdaterTime().equals(other.getUpdaterTime()))
            && (this.getCreaterId() == null ? other.getCreaterId() == null : this.getCreaterId().equals(other.getCreaterId()))
            && (this.getUpdaterId() == null ? other.getUpdaterId() == null : this.getUpdaterId().equals(other.getUpdaterId()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWxSnapshot() == null) ? 0 : getWxSnapshot().hashCode());
        result = prime * result + ((getToUserName() == null) ? 0 : getToUserName().hashCode());
        result = prime * result + ((getFromUserName() == null) ? 0 : getFromUserName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getMsgType() == null) ? 0 : getMsgType().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreaterTime() == null) ? 0 : getCreaterTime().hashCode());
        result = prime * result + ((getUpdaterTime() == null) ? 0 : getUpdaterTime().hashCode());
        result = prime * result + ((getCreaterId() == null) ? 0 : getCreaterId().hashCode());
        result = prime * result + ((getUpdaterId() == null) ? 0 : getUpdaterId().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", wxSnapshot=").append(wxSnapshot);
        sb.append(", toUserName=").append(toUserName);
        sb.append(", fromUserName=").append(fromUserName);
        sb.append(", createTime=").append(createTime);
        sb.append(", msgType=").append(msgType);
        sb.append(", content=").append(content);
        sb.append(", createrTime=").append(createrTime);
        sb.append(", updaterTime=").append(updaterTime);
        sb.append(", createrId=").append(createrId);
        sb.append(", updaterId=").append(updaterId);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}