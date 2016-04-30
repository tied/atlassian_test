package com.example.plugins.tutorial.jira.customfields;


import com.atlassian.activeobjects.external.ActiveObjects;

import java.util.List;
import net.java.ao.Query;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.Lists.*;

public final class IssueEntityServiceImpl implements IssueEntityService {

	private final ActiveObjects aoService;
	
	public IssueEntityServiceImpl(ActiveObjects ActiveObjects){
		this.aoService = checkNotNull(ActiveObjects);
	}

    @Override
    public List<IssueEntity> all()
    {
        return newArrayList(aoService.find(IssueEntity.class, Query.select()));
    }
	@Override
	public IssueEntity add(long issueId, String userKey) {
		IssueEntity newEntity = aoService.create(IssueEntity.class);
		newEntity.setIssueId(issueId);
		newEntity.setUserKey(userKey);
		newEntity.setCommentCount(0);
		newEntity.save();
		return newEntity;	
	}
	@Override
	public IssueEntity getByIssueId(long issueId) {
			IssueEntity results[] = aoService.find(IssueEntity.class, Query.select().where("ISSUE_ID = ?", issueId));		
			if(results.length > 0)
				return results[0];
			else 
				return null;
	}
	@Override
	public void incrementCommentCount(long issueId) {
			IssueEntity result = aoService.find(IssueEntity.class, Query.select().where("ISSUE_ID = ?", issueId))[0];
			result.setCommentCount(result.getCommentCount() + 1);
			result.save();
	}
	@Override
	public void decrementCommentCount(long issueId) {
			IssueEntity result = aoService.find(IssueEntity.class, Query.select().where("ISSUE_ID = ?", issueId))[0];
			result.setCommentCount(result.getCommentCount() -1);
			result.save();
		
	}
	@Override
	public void setUserKey(long issueId, String userKey) {
			IssueEntity result = aoService.find(IssueEntity.class, Query.select().where("ISSUE_ID = ?", issueId))[0];
			result.setUserKey(userKey);
			result.save();
	}

}
