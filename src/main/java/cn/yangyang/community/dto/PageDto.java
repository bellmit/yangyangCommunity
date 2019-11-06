package cn.yangyang.community.dto;

import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageDto {
    //存放页码数据
    private List<PublishDto> publishData;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFristPage;
    private boolean showEndPage;
    //当前页面
    private Integer page;
    //页码数
    private Integer totalCount;
    private List<Integer> pagelist=new ArrayList<>();

    public void Method(Integer count,Integer page,Integer pagesize){
        Integer totalCount=(int)Math.ceil(count*1.0/pagesize);
        if (totalCount==0) totalCount=1;
        this.totalCount=totalCount;
        this.page=page;
        //是否展示上一页和下一页
        this.showPrevious=(page==1)?false:true;
        this.showNext=(page==totalCount)?false:true;
        //放入页码数
        for (int i=-3;i<4;i++){
            if (page+i>0&&page+i<=totalCount){
                this.pagelist.add(page+i);
            }
        }
        //是否展示回到首页和尾页
        this.showFristPage=this.pagelist.contains(1)?false:true;
        this.showEndPage=this.pagelist.contains(totalCount)?false:true;
    }

    public List<PublishDto> getPublishData() {
        return publishData;
    }

    public void setPublishData(List<PublishDto> publishData) {
        this.publishData = publishData;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowFristPage() {
        return showFristPage;
    }

    public void setShowFristPage(boolean showFristPage) {
        this.showFristPage = showFristPage;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPagelist() {
        return pagelist;
    }

    public void setPagelist(List<Integer> pagelist) {
        this.pagelist = pagelist;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
