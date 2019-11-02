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
@Table(name = "t_mail")
public class TMail implements Serializable {
    /**
     * 邮箱表主键
     */
    @Id
    @Column(name = "mail_id")
    @GeneratedValue(generator = "JDBC")
    private Integer mailId;

    /**
     * 姓名
     */
    @Column(name = "mail_name")
    private String mailName;

    /**
     * 邮箱地址
     */
    @Column(name = "mail_mail")
    private String mailMail;

    /**
     * 创建时间
     */
    @Column(name = "mail_date")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date mailDate;

    private static final long serialVersionUID = 1L;
}
