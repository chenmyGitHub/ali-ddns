package com.touchyou.ddns.aliddnsserver.common;

import lombok.Getter;

import java.util.Objects;

/**
 * Create by  Touchyou on 2021/6/18.
 * @author Touchyou
 */
@Getter
public class Result {

    private Integer code;
    private String msg;

    public static Result error() {
        return error("error");
    }

    public static Result error(String msg) {
        return new Result(-1, msg);
    }

    public static Result success() {
        return success("success");
    }

    public static Result success(String msg) {
        return new Result(200, msg);
    }

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result result = (Result) o;
        return code.equals(result.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
