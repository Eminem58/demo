package com.example.demo.enjoy.concurrent.lock;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public interface IGoodsService {
    /**
     * 获取商品数量
     */
    Integer getNum();
    /**
     * 设置商品数量
     */
    void setNum(int num);
}
