package xin.dztyh.personal.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tyh
 * @Package cn.edu.cdu.utils
 * @Description: 分页工具类
 * @date 2018/6/13 17:25
 */
public class PagingUtils {

    //默认页码大小
    private final int SIZE=5;

    //现在的页码
    private int nowPage;

    //所有的数据条数
    private int allDataNum;

    //所有的页码条数
    private int allPageNum;

    //当前的所需要查询的起始条数
    private int offset;

    //页码大小
    private int pageSize;

    //数据集合
    private List<?> list=new ArrayList<>();

    //四种构造函数，满足int类型和String类型的所有输入，需要传入当前页面和页面大小
    /**
     * 构造函数
     * @param SnowPage 当前页码
     * @param pageSize_out 页面大小
     */
    public PagingUtils(String SnowPage, int pageSize_out){
        if(SnowPage!=null&&!SnowPage.equals("")){//判断是否是第一次查看信息
            this.nowPage=Integer.parseInt(SnowPage);//取出当前页码
            this.offset=(this.nowPage-1)*this.pageSize;
        }
        if(pageSize_out!=0){
            this.pageSize=pageSize_out;
        }else{
            this.pageSize=SIZE;
        }
    }

    /**
     * 构造函数
     * @param SnowPage 当前页码
     * @param SpageSize 页面大小
     */
    public PagingUtils(String SnowPage, String SpageSize){
        if(SpageSize!=null&&!SpageSize.equals("")){
            this.pageSize=Integer.parseInt(SpageSize);
        }else{
            this.pageSize=SIZE;
        }
        if(SnowPage!=null&&!SnowPage.equals("")){//判断是否是第一次查看信息
            this.nowPage=Integer.parseInt(SnowPage);//取出当前页码
            this.offset=(this.nowPage-1)*this.pageSize;
        }else{
            this.nowPage=1;
            this.offset=(this.nowPage-1)*this.pageSize;
        }
    }

    /**
     * 构造函数
     * @param nowPage_out 当前页码
     * @param pageSize_out 页面大小
     */
    public PagingUtils(int nowPage_out, int pageSize_out){
        if(nowPage_out!=0){
            this.nowPage=nowPage_out;
            this.offset=(this.nowPage-1)*this.pageSize;
        }else {
            this.nowPage=1;
            this.offset=(this.nowPage-1)*this.pageSize;
        }
        if(pageSize_out!=0){
            this.pageSize=pageSize_out;
        }else{
            this.pageSize=SIZE;
        }
    }

    /**
     * 构造函数
     * @param nowPage_out 当前页码
     * @param SpageSize 页面大小
     */
    public PagingUtils(int nowPage_out, String SpageSize){
        if(SpageSize!=null&&!SpageSize.equals("")){//判断是否是第一次查看信息
            this.pageSize=Integer.parseInt(SpageSize);//取出当前页码
        }else{
            this.pageSize=SIZE;
        }
        if(nowPage_out!=0){
            this.nowPage=nowPage_out;
            this.offset=(this.nowPage-1)*this.pageSize;
        }else{
            this.nowPage=1;
            this.offset=(this.nowPage-1)*this.pageSize;
        }
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
        this.offset=(this.nowPage-1)*this.pageSize;
    }

    public int getAllDataNum() {
        return allDataNum;
    }

    //获取所有的数据条数，并计算页码
    public void setAllDataNum(int allDataNum) {
        this.allDataNum = allDataNum;
        //总页数
        this.allPageNum=allDataNum/pageSize;//计算一共的页数
        if(allDataNum%pageSize!=0){
            this.allPageNum++;
        }
        if(nowPage<1){//判断是否超出页码数
            nowPage=1;
        }else if(nowPage>this.allPageNum&&this.allPageNum!=0){
            nowPage=this.allPageNum;
        }
    }

    public int getAllPageNum() {
        return allPageNum;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
