package nc.api.cy.rest.entity.crm.basepojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 开放平台接口响应，
 *
 * json 定义:
 *
 * <pre>
 * {
 *     "code": "200",
 *     "message": "成功！",
 *     "data": {}
 * }
 * </pre>
 *
 * 请根据 {@code code} 判断是否请求成功，若 {@code code} 为 {@code "200"} 则请求成功，请求数据在 {@code data} 中；
 * 若请求失败，{@code message} 中有详细错误信息，此时 {@code data} 字段不存在。
 *
 * @ClassName NCCGenericResponse
 * @Description TODO NCC接口响应封装
 * @Author NCC
 * @Date 2021/7/6 15:42
 * @Version 1.0
 *
 * @param <T> 具体业务数据
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NCCGenericResponse<T> {

    private String code;

    private String msg;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
