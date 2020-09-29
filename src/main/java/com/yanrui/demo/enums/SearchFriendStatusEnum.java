package com.yanrui.demo.enums;

/**
 * @author 许睿
 * @version 1.0
 * @description 添加好友状态枚举
 * @date 2020/9/28 21:06
 */
public enum SearchFriendStatusEnum {
    /**
     * 成功
     */
    SUCCESS(0, "OK"),
    USER_NO_EXIST(1, "无此用户"),
    NOT_YOURSELF(2, "不能添加自己"),
    ALREADY_FRIENDS(3, "该用户已经是我的好友");

    public final Integer status;
    public final String msg;

    SearchFriendStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    /**
     * 根据枚举的key获取value
     * @param status
     * @return
     */
    public static String getMsgByKey(Integer status) {
        for (SearchFriendStatusEnum type : SearchFriendStatusEnum.values()) {
            if (type.getStatus().equals(status)) {
                return type.msg;
            }
        }
        return null;
    }

}
