package service;

import org.springframework.stereotype.Component;

@Component
public class ExcelImpl implements Excel {
    @Override
    public void read() {
        System.out.println("read");
    }

    @Override
    public void write() {
        System.out.println("write");
    }
}
