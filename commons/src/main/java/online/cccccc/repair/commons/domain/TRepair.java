package online.cccccc.repair.commons.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
@create 2019/10/30 - 16:20
@author    你是电脑
*/
@Data
@Table(name = "t_repair")
public class TRepair implements Serializable {
    /**
     * 单号
     */
    @Id
    @Column(name = "repair_id")
    @GeneratedValue(generator = "JDBC")
    private Integer repairId;

    /**
     * 校区 0-老校区1-新校区
     */
    @Column(name = "repair_region")
    private Byte repairRegion;

    /**
     * 宿舍
     */
    @Column(name = "repair_dormitory")
    private String repairDormitory;

    /**
     * 联系方式
     */
    @Column(name = "repair_contact")
    private String repairContact;

    /**
     * 查阅次数
     */
    @Column(name = "repair_number")
    private Integer repairNumber;

    /**
     * 问题简单描述
     */
    @Column(name = "repair_description")
    private String repairDescription;

    @Column(name = "repair_date")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date repairDate;

    private static final long serialVersionUID = 1L;
}
