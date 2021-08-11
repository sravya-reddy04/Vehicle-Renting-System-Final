package com.vrs.dao;

import com.vrs.entities.*;

public interface IUserRepository {

	public User validateUser(User user);
	public User addUser(User user);
	public User removeUser(User user);
	public User signOut(User user);
}
