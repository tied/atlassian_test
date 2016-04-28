package com.example.plugins.tutorial.jira.customfields;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.Lists.*;

import com.atlassian.activeobjects.external.ActiveObjects;
import java.util.List;

//import net.java.ao.Entity;
//import net.java.ao.ActiveObjects;
import net.java.ao.Query;

import java.sql.SQLException;

public final class MyEntityServiceImpl implements MyEntityService {
	private final ActiveObjects ActiveObjects;
	
	public MyEntityServiceImpl(ActiveObjects ActiveObjects){
		 this.ActiveObjects = checkNotNull(ActiveObjects);
	}
	@Override
    public MyEntity add(String name){
		final MyEntity my_entity = ActiveObjects.create(MyEntity.class);
		my_entity.setName(name);
		my_entity.save();
		return my_entity;	
    }

    @Override
    public List<MyEntity> all()
    {
        return newArrayList(ActiveObjects.find(MyEntity.class, Query.select()));
    	
    }

}
