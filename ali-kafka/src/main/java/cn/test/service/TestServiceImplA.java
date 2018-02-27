package cn.ykc.echarge.dev.test.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImplA implements ITestService {
    @Override
    public void test() {
        System.out.println("具体实现");
    }
}
