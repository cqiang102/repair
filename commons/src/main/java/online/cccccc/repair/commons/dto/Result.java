package online.cccccc.repair.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 你是电脑
 * @create 2019/11/1 - 15:41
 */
@Data
public class Result implements Serializable {
    /**
     * 状态码
     */
    private int status;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

    public static Result makeResult(int status,String message,Object data){
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);

        return result;
    }

}
