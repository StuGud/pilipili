package com.seu.pilipili.service;

import com.seu.pilipili.entity.User;

//注册，是否存在用户名，登陆
public interface UserService {
    User register(User user);

    /**
     *
     * @param username 参数username+password？user?
     * @return
     */
    User login(String username,String password);

    boolean existsUsername(String username);

    User modify(User user);

    /**
     *
     * @param userId
     * @param change true,+1;false,-1
     * @return
     */
    User modifyApprovalNum(long userId,boolean change);
}
