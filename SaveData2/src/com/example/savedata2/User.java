package com.example.savedata2;

import java.io.Serializable;

public class User implements Serializable {
	
	String name;
	int age;
	String FavoriteFood;
	boolean gender;
	public User(String name, int age, String favoriteFood, boolean gender) 
	{
		super();
		this.name = name;
		this.age = age;
		FavoriteFood = favoriteFood;
		this.gender = gender;
	}
	@Override
	public String toString()
	{
		return "User [name=" + name + ", age=" + age + ", FavoriteFood=" + FavoriteFood + ", gender=" + gender + "]";
	}

	

	

}
