package aop;

import core.service.HandleRequestService;
import core.service.Service;
import dto.Alpha;
import dto.DataType;

import java.lang.reflect.Method;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class HandleRequestServiceAop extends SimpleAop<HandleRequestService> {

    public HandleRequestServiceAop(HandleRequestService object) {
        super(object);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.equals(HandleRequestService.class.getDeclaredMethod("isSupport",Alpha.class))){
            Alpha alpha= (Alpha) args[0];
            return alpha.getDataType() == DataType.REQUEST && getObject().isSupport(alpha);
        }
        return method.invoke(object,args);
    }
}
