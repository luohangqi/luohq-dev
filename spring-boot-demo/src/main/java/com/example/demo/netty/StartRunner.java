package com.example.demo.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartRunner implements CommandLineRunner{

    @Override
    public void run(String... strings) throws Exception {
    	System.out.println("StartRunner.run()");
    	new EchoServer4NIO(8809).start();
    }
}
