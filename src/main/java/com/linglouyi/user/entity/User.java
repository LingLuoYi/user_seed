package com.linglouyi.user.entity;

import com.linglouyi.user.entity.base.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * @author linglouyi
 */
@MappedSuperclass
@Setter @Getter
public class User extends Base {

    @Size(max = 50, message = "用户名不能超过50个字符")
    @Column(unique = true)
    private String username;

    private String password;


}
