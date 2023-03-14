package com.ue.termi.util.rpc;

import cn.hutool.json.JSONUtil;
import com.ue.termi.enums.ResultCodeMsgEnum;
import com.ue.termi.exception.RpcException;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * @author: youyi
 * @date: 2021/3/15 14:37
 * @description: 简单rpc调用，收敛到这个方法
 */
@Slf4j
public class RpcHelper {

    /**
     * rpc请求，携带降级方法
     * @param t 请求值
     * @param rpc rpc 实际请求方法
     * @param requestConverter 请求值转换方法
     * @param responseConverter 返回值转换方法
     * @param failMethod 默认返回值值转换方法，降级方法
     * @return 返回值
     */
    static <T, R, T1, R0, R1> R1 doRpcWithDefault(T t, RPC<T1, R> rpc,
                                       Function<T, T1> requestConverter,
                                       Function<R, R1> responseConverter,
                                       Function<T, R1> failMethod) {
        try{
            return doRpc(t,rpc,requestConverter,responseConverter);
        }catch (Exception e) {
            log.error("rpc err, use default result",e);
        }
        return failMethod.apply(t);
    }

    /**
     * rpc请求，不适用降级，优化了不吞err返回（需要在responseConverter方法中处理将返回err处理成对应的ServiceException）
     * @param t 请求值
     * @param rpc rpc 实际请求方法
     * @param requestConverter 请求值转换方法
     * @param responseConverter 返回值转换方法
     * @return 返回值
     */
    static <T, R, T1, R0, R1> R1 doRpc(T t, RPC<T1, R> rpc,
                                       Function<T, T1> requestConverter,
                                       Function<R, R1> responseConverter) {
        try {
            T1 t1 = requestConverter.apply(t);
            R r = rpc.doRpc(t1);
            return responseConverter.apply(r);
        } catch (RpcException e) {
            log.error("rpc error1, req={}", JSONUtil.parse(t), e);
            throw e;
        } catch (Exception e){
            log.error("rpc error2, req={}", JSONUtil.parse(t), e);
            throw new RpcException(ResultCodeMsgEnum.RPC_ERR.getCode(),"系统异常，请稍后重试");
        }
    }

    interface RPC<T, R> {
        /**
         * 具体rpc调用方法
         */
        R doRpc(T t) throws Exception;
    }

    interface ResponseConverter<T, R> {
        /**
         *
         */
        R responseConverter(T t) throws Exception;
    }


    public static class DefaultResponseConverter implements ResponseConverter<Object,Object>{
        @Override
        public Object responseConverter(Object o) throws Exception {
            return o;
        }
    }
}
