package com.example.demo.future;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MyFutureTest {

	static ExecutorService threadPool = Executors.newCachedThreadPool();

	public void doFuture() {
		Future<String> future = threadPool.submit(new Callable<String>() {
			public String call() throws Exception {
				for (int i = 0; i < 100; i++) {
					System.out.println(i + "F");
					if (i == 50) throw new RuntimeException("h");
				}

				return "future执行完成";
			}
		});
		
		System.out.println("haha");
		String string = "";
		try {
			string = future.get();
			System.out.println(string);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String doFutureCompletionService() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
        for(int i = 1; i < 5; i++) {
            final int taskID = i;
            cs.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                	System.out.println(taskID + "cs");
                    return taskID;
                }
            });
        }
        
        System.out.println("拿结果");
        
        // 可能做一些事情
        for(int i = 1; i < 5; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("全部执行完");
        return "g";
    }
	
	
	public void doFutureTimeOut() {
		Future<String> future = threadPool.submit(new Callable<String>() {
			public String call() throws Exception {

				for (int i = 0; i < 100; i++) {
					Thread.sleep(1000);
					System.out.println(i + 1 + "f");
				}
				return "future执行完成";
			}
		});
		
		System.out.println("haha");
		String string = "";
		try {
			string = future.get(5000, TimeUnit.MILLISECONDS);
			System.out.println(string);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
