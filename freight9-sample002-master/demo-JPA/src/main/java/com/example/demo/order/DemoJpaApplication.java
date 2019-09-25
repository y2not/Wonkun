package com.example.demo.order;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerMapper;
import com.example.demo.order.mapper.OrderMapper;
import com.example.demo.order.model.Order;
import com.example.demo.order.model.OrderItem;
import com.example.demo.order.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class DemoJpaApplication implements CommandLineRunner{

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CityRepository repository2;

    @Autowired
    private UserRepository repository3;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderMasterRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private OrderMapper orderMapper;

//    @Autowired
//    private CustomerMapper customerMapper;


	public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);

//        try (ConfigurableApplicationContext ctx = SpringApplication.run(DemoJpaApplication.class, args))       {
//            DemoJpaApplication m = ctx.getBean(DemoJpaApplication.class);
//            m.method();
//            m.run(args);
//        }


	}
	
    @Override
    public void run(String... args) {

        log.debug("StartApplication...");

//        log.debug("\nfindAll()");
      // repository.findAll().forEach(x -> System.out.println(x));

        log.debug("\nfindById(1L)");
        repository.findById(1l).ifPresent(x -> System.out.println(x));
//
//        log.debug("\nfindByName('Node')");
//        repository.findByName("Node").forEach(x -> System.out.println(x));
//
//
//        log.debug("\n==================city=================");
//        log.debug("\nfindById(10L)");
//        repository2.findById(10L).ifPresent(x -> System.out.println(x));
//
//        log.debug("\nCity findByName('Rafah')");
//        repository2.findByName("Rafah").forEach(x -> System.out.println(x));
//
//        log.debug("\nCity findByCountrycodeReturnStream('AFG')");
//       repository2.findByCountrycode("AFG").forEach(x -> System.out.println(x));
//
//
//        log.debug("\nCity findByUsername('jojang.com')");
//        repository3.findByUsername("jojang.com").forEach(x -> System.out.println(x));

//        EntityManagerFactory factory = applicationContext.getBean(EntityManagerFactory.class); EntityManager manager = factory.createEntityManager();
//        EntityManager em = factory.createEntityManager();

//        log.debug("====================================================================================");

//        OrderItem orderItem = new OrderItem();
//        orderItem.setId(1L);
//        orderItem.setProductCode(301L);
//        orderItem.setProductName("ABC");
//        orderItem.setQty(200);
//        orderItem.setPrice(5000);


//        log.debug("====================================================================================");

//        OrderItemPK pk= new OrderItemPK();
//        pk.setId(1L);
//        pk.setProductCode(301L);
//
//        Optional<OrderItem> orderItem2 = Optional.of(new OrderItem());
//        orderItem2=orderItemRepository.findById(pk);
//        log.debug("\nPK Search ('Order')");
//        log.debug("ProductName:" +orderItem2);



//        em.persist(orderItem);
//        em.flush();
//        em.clear();
//
//        OrderItemPK pk= new OrderItemPK();
//        pk.setId(1L);
//        pk.setProductCode(301L);
//
//        orderItem =em.find(OrderItem.class,pk);
//        log.debug("\nPK Search ('Order')");
//        log.debug("ProductName:" +orderItem.getProductName());

        method();
        myBatisTest();
       // myBatisTest2();
    }

//    public void myBatisTest2() {
//        Customer customer= customerMapper.findByUserNameMybatis("jangjaeok2");
//        log.debug("Customer:"+customer);
//    }
    public void myBatisTest() {

        String userName1="jang jaeock";
        String userName2="Ha kyung";
        List<Order> list = orderMapper.findAllByEmp(userName1);
        log.debug("\n====================================================================================");
        log.debug("\n Mybatis test: orderMapper.findAllByEmp(userName) ");
        log.debug("\n====================================================================================");
        list.forEach(System.out::println);

        list = orderMapper.search(userName2);
        log.debug("\n====================================================================================");
        log.debug("\n Mybatis test: orderMapper.search(userName) 111111111111111111111111111111111111111111");
        log.debug("\n====================================================================================");
        list.forEach(System.out::println);

        long id=2019083108354900000L;
        id=0L;
        list = orderMapper.search2(userName2,id );
        log.debug("\n====================================================================================");
        log.debug("\n Mybatis test: orderMapper.search2(userName) 2222222222222222222222222222222");
        log.debug("\n====================================================================================");
        list.forEach(System.out::println);

//        for(Order order :list) {
//            log.debug(order.toString());
//            List<OrderItem> orderItemList = order.getOrderItemList();
//                for(OrderItem orderItem:orderItemList) {
//                    //log.debug(orderItem.toString());
//                }
//            }
        log.debug("\n====================================================================================");
        log.debug("\n=============search=======================================================================");
//        List<Order> list2 =orderMapper.search("Ha kyung");
//        list2.forEach(System.out::println);

    }



	public void method() {


//	    this.jdbc.execute("CREATE TABLE TEST_TABLE (ID INTEGER NOT NULL IDENTITY, VALUE VARCHAR(256))");
//	    this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "hoge");
//	    this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "fuga");
//	    this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "piyo");
//	    List<Map<String, Object>> list = this.jdbc.queryForList("SELECT * FROM TEST_TABLE");
//	    list.forEach(System.out::println);

        log.debug("\n====================================================================================");
        log.debug("\njdbcTemplate test");
        log.debug("\n====================================================================================");
        List<Map<String, Object>> list = this.jdbc.queryForList("SELECT * FROM world.ordermaster");
        list.forEach(System.out::println);
        log.debug("\n====================================================================================");

	}



}
