package com.example.plugins.tutorial.jira.customfields;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.extension.JiraStartedEvent;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import static com.google.common.base.Preconditions.*;

import com.example.plugins.tutorial.jira.customfields.IssueEntityService;

public class MyIssueListener implements InitializingBean, DisposableBean {
	private IssueEntityService entityService;
	private final EventPublisher eventPublisher;
	
	/**
     * Constructor.
     * @param eventPublisher injected {@code EventPublisher} implementation.
     */
    public MyIssueListener(EventPublisher eventPublisher, IssueEntityService entityService) {
        this.eventPublisher = eventPublisher;
        this.entityService = entityService;
    }

    /**
     * Called when the plugin has been enabled.
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // register ourselves with the EventPublisher
        eventPublisher.register(this);
    }

    /**
     * Called when the plugin is being disabled or removed.
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        // unregister ourselves with the EventPublisher
        eventPublisher.unregister(this);
    }
	
	@EventListener
	public void onStart(final JiraStartedEvent event) {
		
	}
	
    @EventListener
    public void onIssueEvent(IssueEvent issueEvent) {
        Long eventTypeId = issueEvent.getEventTypeId();
        Issue issue = issueEvent.getIssue();
        if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
        	entityService.add(issue.getId(), issue.getCreator().getKey());
        }
        else if (eventTypeId.equals(EventType.ISSUE_COMMENTED_ID)) {
            entityService.incrementCommentCount(issue.getId());
        } 
        else if (eventTypeId.equals(EventType.ISSUE_COMMENT_DELETED_ID)) {
            entityService.decrementCommentCount(issue.getId());
        } 
        else if (eventTypeId.equals(EventType.ISSUE_ASSIGNED_ID)) {
            entityService.setUserKey(issue.getId(), issue.getAssigneeUser().getKey());
        }
    }

}
