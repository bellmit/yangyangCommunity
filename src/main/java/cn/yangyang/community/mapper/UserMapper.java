package cn.yangyang.community.mapper;

import cn.yangyang.community.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserMapper {

    //查询账号和密码是否正确
    @Select("select * from user where user_Tel=#{user_Tel} and user_Pwd=#{user_Pwd}")
    User JudgementAccount(User user);

    //查询用户信息
    @Select("select * from user where user_ID=#{user_ID}")
    User FindByUser(Integer user_ID);

    //更改user_Token
    @Update("update user set user_Token=#{user_Token} where user_ID=#{user_ID}")
    Integer UpdateByToken(User user);
}
