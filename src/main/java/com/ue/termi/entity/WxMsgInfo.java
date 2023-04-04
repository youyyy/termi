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
}