package cn.yangyang.community.mapper;

import cn.yangyang.community.pojo.Publish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

//问题
@Mapper
@Component
public interface PublishMapper {

    //查询语句
    @Select("select * from publish order by publish_Datetime DESC Limit #{offsize},#{pagesize}")
    List<Publish> Select(Integer offsize,Integer pagesize);

    //根据用户询查
    @Select("select * from publish where user_ID=#{user_ID} order by publish_Datetime DESC Limit #{offsize},#{pagesize}")
    List<Publish> SelectByUserID(Integer user_ID,Integer offsize,Integer pagesize);

    //添加语句
    @Insert("insert into publish(publish_Title,publish_Desc,publish_Datetime,user_ID,Tag)" +
            "values(#{publish_Title},#{publish_Desc},#{publish_Datetime},#{user_ID},#{Tag})")
    Integer insert(Publish publish);

    //查询总数
    @Select("select count(1) from publish")
    Integer Count();

    //查询用户发布数量
    @Select("select count(1) from publish where user_ID=#{user_ID}")
    Integer CountByUserID(Integer user_ID);
}
