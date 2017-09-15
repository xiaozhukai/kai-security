package com.github.kai.security.admin.vo;

import com.github.kai.security.admin.entity.User;

import java.util.List;

/**
 * TODO: team队员
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 19:30
 */
public class GroupUsers {

    List<User> members;

    List<User> leaders;

    public GroupUsers() {
    }

    public GroupUsers(List<User> members, List<User> leaders) {
        this.members = members;
        this.leaders = leaders;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<User> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<User> leaders) {
        this.leaders = leaders;
    }
}
