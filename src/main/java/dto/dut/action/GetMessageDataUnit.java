package dto.dut.action;

import dto.Action;
import dto.dut.DataUnit;

/**
 * @author 杨能
 * @create 2020/9/25
 * 与 Action.GET_MESSAGE 搭配使用
 */
public class GetMessageDataUnit extends DataUnit {

    protected long startIndex = 0;

    protected long endIndex = Long.MAX_VALUE;

    /**
     * 转为与Action.GET_ALL_MESSAGE同义
     */
    public GetMessageDataUnit() {

    }

    public GetMessageDataUnit(long startIndex, long endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public String getTypeKey() {
        return this.getClass().getSimpleName();
    }
}
