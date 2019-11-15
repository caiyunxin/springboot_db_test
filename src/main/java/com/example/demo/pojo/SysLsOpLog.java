package com.example.demo.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_ls_op_log")
public class SysLsOpLog {
    /**
     * 编号
     */
    @Id
    private Long id;

    @Column(name = "sys_id")
    private Long sysId;

    @Column(name = "org_id")
    private Long orgId;

    /**
     * 日志类型：类型名称字符串简写，不超过10位，默认OTHER
     */
    @Column(name = "log_type")
    private String logType;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "room_num")
    private String roomNum;

    /**
     * 操作类型：类型名称字符串，不超过10位，默认OTHER
     */
    @Column(name = "op_type")
    private String opType;

    /**
     * 操作的数据记录
     */
    @Column(name = "op_data")
    private String opData;

    /**
     * 本次操作时间
     */
    @Column(name = "op_time")
    private Date opTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return sys_id
     */
    public Long getSysId() {
        return sysId;
    }

    /**
     * @param sysId
     */
    public void setSysId(Long sysId) {
        this.sysId = sysId;
    }

    /**
     * @return org_id
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取日志类型：类型名称字符串简写，不超过10位，默认OTHER
     *
     * @return log_type - 日志类型：类型名称字符串简写，不超过10位，默认OTHER
     */
    public String getLogType() {
        return logType;
    }

    /**
     * 设置日志类型：类型名称字符串简写，不超过10位，默认OTHER
     *
     * @param logType 日志类型：类型名称字符串简写，不超过10位，默认OTHER
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * @return hotel_id
     */
    public Long getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId
     */
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * @return hotel_name
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @param hotelName
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * @return room_num
     */
    public String getRoomNum() {
        return roomNum;
    }

    /**
     * @param roomNum
     */
    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * 获取操作类型：类型名称字符串，不超过10位，默认OTHER
     *
     * @return op_type - 操作类型：类型名称字符串，不超过10位，默认OTHER
     */
    public String getOpType() {
        return opType;
    }

    /**
     * 设置操作类型：类型名称字符串，不超过10位，默认OTHER
     *
     * @param opType 操作类型：类型名称字符串，不超过10位，默认OTHER
     */
    public void setOpType(String opType) {
        this.opType = opType;
    }

    /**
     * 获取操作的数据记录
     *
     * @return op_data - 操作的数据记录
     */
    public String getOpData() {
        return opData;
    }

    /**
     * 设置操作的数据记录
     *
     * @param opData 操作的数据记录
     */
    public void setOpData(String opData) {
        this.opData = opData;
    }

    /**
     * 获取本次操作时间
     *
     * @return op_time - 本次操作时间
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     * 设置本次操作时间
     *
     * @param opTime 本次操作时间
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}