package test;

import dto.Alpha;

/**
 * @author 杨能
 * @create 2020/10/22
 */

public interface TestBody {
    public boolean test(Alpha alpha);
    public void success(Alpha alpha);
    public void fail(Alpha alpha);
}
