//package com.ue.termi.util.rpc;/*
//          .--,       .--,
//         ( (  \.---./  ) )
//          '.__/o   o\__.'
//             {=  ^  =}
//              >  -  <
//             /       \
//            //       \\
//           //|   .   |\\
//           "'\       /'"_.-~^`'-.
//              \  _  /--'         `
//            ___)( )(___
//           (((__) (__)))
//   高山仰止,景行行止.虽不能至,心向往之。
//*/
//
//import com.xd.irs.common.app.web.api.common.ApiResponse;
//import com.xd.irs.thomas.infra.common.errorcode.CommonErrorCode;
//import com.xd.irs.thomas.infra.common.exception.BizException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.helpers.FormattingTuple;
//import org.slf4j.helpers.MessageFormatter;
//
//import java.util.Collection;
//import java.util.function.Supplier;
//
///**
// * @program: service-workflow
// * @description:
// * @author: youyi
// * @create: 2022/4/25
// **/
//@Slf4j
//public class RpcUtil {
//
//
//    /**
//     * 进行一次远端调用，如果远端具有错误码，则抛出对应的 {@link BizException} 异常
//     *
//     * @param call   远端调用
//     * @param errMsg 错误信息
//     * @param args   错误信息参数
//     * @param <T>    请求返回值类型
//     * @return 如果调用错误，则抛出对应的 {@link BizException}
//     */
//    public static <T> T of(
//            Supplier<ApiResponse<T>> call,
//            String errMsg,
//            Object... args
//    ) throws BizException {
//        ApiResponse<T> resp = call.get();
//        if(null == resp){
//            throw new BizException(CommonErrorCode.API_ERROR.code(), "请求系统繁忙，请稍后重试");
//        }
//        if (0 == resp.getCode()) {
//            return resp.getData();
//        }
//
//
//        if (StringUtils.isEmpty(errMsg)) {
//            throw new BizException(resp.getCode(), resp.getMessage());
//        }
//
//        FormattingTuple tuple = MessageFormatter.format(errMsg, args);
//        throw new BizException(resp.getCode(), tuple.getMessage() +
//                "调用错误信息:" + resp.getMessage());
//    }
//
//    public static <T> T of(Supplier<ApiResponse<T>> call) {
//        return of(call, null);
//    }
//
//    public static <T> T of(ApiResponse<T> resp) {
//        return of(() -> resp);
//    }
//
//    public static <T> T ofNotNull(ApiResponse<T> resp, String msgPre) {
//        T of = of(() -> resp);
//        if (null == of) {
//            throw new BizException(CommonErrorCode.API_ERROR.code(), msgPre + "：返回数据为空");
//        }
//        return of;
//    }
//
//
//    public static <T> T ofNotNull(ApiResponse<T> resp, String msgPre, String msgEnd) {
//        T of = of(() -> resp);
//        if (null == of) {
//            throw new BizException(CommonErrorCode.API_ERROR.code(), msgPre + "：返回数据为空。" + msgEnd);
//        }
//        return of;
//    }
//
//
//    public static <T extends Collection<?>> T ofNotEmpty(ApiResponse<T> resp, String msgPre) {
//        T of = of(() -> resp);
//        if (null == of) {
//            throw new BizException(CommonErrorCode.API_ERROR.code(), msgPre + "：返回数据为空");
//        }
//        if (CollectionUtils.isEmpty(of)) {
//            throw new BizException(CommonErrorCode.API_ERROR.code(), msgPre + "：返回数据为空集合");
//        }
//        return of;
//    }
//}
//
