package cn.yangyang.community.mapper;

import cn.yangyang.community.pojo.Publish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

//问题
@Mapper
@Component
public interface PublishMapper {

    //添加语句
    @Insert("insert into publish(publish_Title,publish_Desc,publish_Datetime,user_ID,Tag)" +
            "values(#{publish_Title},#{publish_Desc},#{publish_Datetime},#{user_ID},#{Tag})")
    Integer insert(Publish publish);
}
