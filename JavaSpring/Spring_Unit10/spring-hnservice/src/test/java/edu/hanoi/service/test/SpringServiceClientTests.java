package edu.hanoi.service.test;

import edu.hanoi.service.model.Group;
import edu.hanoi.service.model.User;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.util.List;

@RunWith(JUnit4.class)
public class SpringServiceClientTests {
    private RestTemplate restTemplate;

    @Before
    public void setUp(){
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = builder.build();
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

//    @Test
//    public void contextLoads(){
//        String address = "http://localhost:8080/list/users/";
//        ResponseEntity<List> entity = restTemplate.getForEntity(address, List.class);
//        List<User> users = (List<User>) entity.getBody();
//    }


//    @Test
//    public void contextLoads(){
//        String address = "http://localhost:8080/list/groups";
//        ResponseEntity<Group[]> groupEntiy = restTemplate.getForEntity(address, Group[].class);
//        Group[] groups = groupEntiy.getBody();
//        Assert.assertTrue(groups.length > 0);
//
//        for (int i = 0;i < groups.length; i++){
//            System.out.println(groups[i].getId());
//        }
//    }

    @Test
    public void contextLoads() {
        String address = "http://localhost:8080/list/groups/";
        ResponseEntity<Group[]> groupEntiy = restTemplate.getForEntity(address, Group[].class);
        Group[] groups = groupEntiy.getBody();
        Assert.assertTrue(groups.length > 0);

        for (int i = 0; i < groups.length; i++) {
            System.out.println(groups[i].getId());
        }

        User user = new User();
        user.setUsername("test3");
        user.setPassword("12345");
        user.setAge(25);
        user.setGroupId(101);
        user.setEmail("test1@gmail.com");

//        address = "http://localhost:8080/add/user";
//        ResponseEntity<String> insertEntity = restTemplate.postForEntity(address, user, String.class);
//        Assert.assertEquals(user.getUsername(), insertEntity.getBody());


//        /// 19
//        address = "http://localhost:8080/get/user/test3";
//        ResponseEntity<User> getEntity = restTemplate.getForEntity(address, Void.class);
//        Assert.assertEquals("123456", getEntity.getBody().getPassword());


        //20
//        address = "http://localhost:8080/delete/user/username-random17";
//        ResponseEntity<Void> delEntity = restTemplate.getForEntity(address, Void.class);
//        
//        address = "http://localhost:8080/get/user/username-random40";
//        ResponseEntity<User> getEntity = restTemplate.getForEntity(address, User.class);
//        Assert.assertEquals(null, getEntity.getBody());


//
//        // 22
        address = "http://localhost:8080/get/user/username-random28";
        ResponseEntity<User> getEntity = restTemplate.getForEntity(address, User.class);
        User user1 = getEntity.getBody();
        Assert.assertNotNull(user1);

        user1.setPassword("654321");
       address = "http://localhost:8080/update/user";
       restTemplate.postForEntity(address, user1, Void.class);

        address = "http://localhost:8080/get/user/username-random28";
        ResponseEntity<User> getEntity2 = restTemplate.getForEntity(address, User.class);
        Assert.assertEquals(user1.getPassword(), getEntity2.getBody().getPassword());

    }
}
