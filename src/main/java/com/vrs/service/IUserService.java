package com.vrs.service;
import com.vrs.entities.*;

public interface IUserService {

	public User validateUser(User user);
	public User addUser(User user);
	public User removeUser(User user);
	public User signOut(User user);
}
