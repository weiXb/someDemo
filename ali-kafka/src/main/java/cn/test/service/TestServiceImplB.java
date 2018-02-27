package cn.ykc.echarge.dev.test.service;

import cn.ykc.echarge.base.common.Result;
import cn.ykc.echarge.base.common.constant.Constants;
import cn.ykc.echarge.dev.postoperator.domain.StartChargeResp;
import org.apache.commons.lang3.StringUtils;
import org.n3r.eql.Eql;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;

@Service
public class TestServiceImplB implements ITestService {

    private static ThreadLocal<Integer> threadcount = new ThreadLocal<>();

    static Integer getCount() {
        Integer count = threadcount.get();
        return count == null ? 1 : count;
    }

    static void addCount() {
        threadcount.set(getCount() + 1);
    }


    @Override
    public void test() {
        System.out.println("具体实现B");
        System.out.println("开始"+Thread.currentThread().getName() + getCount());
        if (getCount() <= 3) {
            System.out.println(getCount());
            addCount();
            test();
        }
    }

    public void test1(ResponseBodyEmitter emitter) {
        String str = new Eql().params("20180105155539541").limit(1).returnType(String.class).execute();
        if (StringUtils.isBlank(str)) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test1(emitter);
        } else {
            try {
                emitter.send(Result.build(Constants.SUCCESS, Constants.SUCCESS_MSG, new StartChargeResp(), ""));
            } catch (IOException e) {
                e.printStackTrace();
            }

            emitter.complete();
        }



    }
}
