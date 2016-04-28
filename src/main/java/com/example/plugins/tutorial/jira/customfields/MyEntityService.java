package com.example.plugins.tutorial.jira.customfields;

import java.util.List;

import com.example.plugins.tutorial.jira.customfields.MyEntity;

public interface MyEntityService {
	MyEntity add(String name);
	List<MyEntity> all();
}
