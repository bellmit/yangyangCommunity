package cn.yangyang.community.mapper;

import cn.yangyang.community.pojo.LoginRecoed;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginRecoedMapper {

    //添加语句
    @Insert("insert into loginrecoed values(null,#{user_ID},#{Recoed_Datetime})")
    int insert(LoginRecoed recoed);
}
