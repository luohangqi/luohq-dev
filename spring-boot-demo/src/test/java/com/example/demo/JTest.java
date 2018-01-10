package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.LoggingProducerListener;

import com.example.demo.entity.User;
import com.example.demo.future.MyFutureTest;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;

/**
 * java8 测试
 * 
 * @author luohq
 * @Data 2017年10月12日
 * @Description TODO
 */
public class JTest {

	List<User> userList = Lists.newArrayList();

	private static final Log logger = LogFactory.getLog(LoggingProducerListener.class);
	private static final Logger log = LoggerFactory.getLogger(JTest.class);

	
	@Test
	public void init() {
		User user1 = new User("AAA", 10, "火星", "篮球");
		userList.add(user1);
		User user2 = new User("BBB", 10, "火星", "游泳");
		userList.add(user2);
		User user3 = new User("CCC", 30, "新加坡", "羽毛球");
		userList.add(user3);
		User user4 = new User("DDD", 40, "新西兰", "乒乓球");
		userList.add(user4);
		User user5 = new User("EEE", 50, "布宜诺斯", "篮球");
		userList.add(user5);
		User user6 = new User("FFF", 60, "布宜诺斯", "游泳");
		userList.add(user6);
		User user7 = new User("GGG", 70, "土星", "足球");
		userList.add(user7);
		User user8 = new User("HHH", 80, "土星", "游泳");
		userList.add(user8);
		User user9 = new User("III", 90, "天王星", "篮球");
		userList.add(user9);
		User user10 = new User("JJJ", 100, "天王星", "篮球");
		userList.add(user10);
		
		List<User> unique = userList.parallelStream().collect(Collectors.collectingAndThen(
				Collectors.toCollection(() -> new TreeSet(Comparator.comparingInt(User::getAge))), ArrayList::new));
//		System.out.println(unique);
//		List<User> collect = userList.stream().sorted(Comparator.comparing(User::getAge).thenComparing(User::getName)).collect(Collectors.toList());
//		System.out.println(collect);
		
		
		List<User> unique2 = userList.parallelStream().collect(Collectors.collectingAndThen(
				Collectors.toCollection(() -> new TreeSet<User>((u1, u2) -> u1.getCity().compareTo(u2.getCity()))), ArrayList::new));
		List<User> collect = userList.stream().distinct().collect(Collectors.toList());
		System.out.println(collect);
		
		/*List<User> uniqueaa = userList.stream().filter(distinctByKey(User::getCity)).collect(Collectors.toList());
		System.out.println(uniqueaa);
		
		Collection<User> values = userList.stream().collect(Collectors.toMap(User::getCity, p -> p, (p, q) -> q)).values();
		System.out.println(values);*/
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}
	
	@Test
	public void test() {
		MyFutureTest myFutureTest = new MyFutureTest();
		myFutureTest.doFutureTimeOut();
		String g= myFutureTest.doFutureCompletionService();
		System.out.println(g);
		Exception ex = new RuntimeException("异常");
		logger.error("nihao", ex);
		
	}

	@Test
	public void test_groupingBy() {
		Map<String, List<User>> collect = userList.stream().collect(Collectors.groupingBy(User::getJob));
		System.out.println(collect);
	}

	public long sequentialSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
		
//		Optional<String> name = Optional.optInsurance.map(User::getName); 
	}

	@Test
	public void test2() {
		log.info("offset = %d, key = %s, value = %s \n", 11, "ss", 2);
		StringBuilder strBuilder=new StringBuilder();
		System.out.println("buil:" + strBuilder.toString());
		if (StringUtil.isNotEmpty(strBuilder.toString())) {
			System.out.println("h");
		}
	}
	
}
