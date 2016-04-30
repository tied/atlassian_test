package com.example.plugins.tutorial.jira.customfields;

import java.util.List;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.example.plugins.tutorial.jira.customfields.IssueEntity;

public interface IssueEntityService {
	//final ActiveObjects aoService;
	IssueEntity add(long issueId, String userKey);
	IssueEntity getByIssueId(long issueId);
	void incrementCommentCount(long issueId);
	void decrementCommentCount(long issueId);
	void setUserKey(long issueId, String userKey);
	List<IssueEntity> all();
}
