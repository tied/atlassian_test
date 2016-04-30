package com.example.plugins.tutorial.jira.customfields;


import net.java.ao.Entity;

public interface IssueEntity extends Entity{
	long getIssueId();
	void setIssueId(long issueID);
	
	String getUserKey();
	void setUserKey(String userKey);
	
	long getCommentCount();
	void setCommentCount(long commentCount);
}
