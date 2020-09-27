package core;

import dto.Alpha;

/**
 * @author 杨能
 * @create 2020/9/26
 * 消息发送的中间件，使用这个类来屏蔽socket与netty的底层数据结构区别
 */
public interface AlphaDirectCenter {

    public void sendAlpha(Alpha alpha);


}
