package com.tianmaying.model;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static List<User> users = new ArrayList<>();
    
    static {
        users.add(new User("david@tianmaying.com", "david", "111111","david的博客描述"));
        users.add(new User("ricky@tianmaying.com", "ricky", "222222","ricky的博客描述"));
        users.add(new User("cliff@tianmaying.com", "cliff", "333333","cliff的博客描述"));
        users.add(new User("harttle@tianmaying.com", "harttle", "444444","harttle的博客描述"));
    }
    
    public static List<User> getAll() {
        return users;
    }
    
    public static User getByUsername(String username) {
    	for(User user: users){
    		if (user.getUsername().equals(username)) {
				return user;
			}
    	}
    	return null;
    }
    
    public static void add(User user) {
        users.add(user);
    }
}