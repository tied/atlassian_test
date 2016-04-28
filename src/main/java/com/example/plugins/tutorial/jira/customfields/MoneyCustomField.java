package com.example.plugins.tutorial.jira.customfields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFieldConstants;
import com.atlassian.jira.issue.changehistory.ChangeHistory;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.customfields.SortableCustomField;
import com.atlassian.jira.issue.customfields.impl.CalculatedCFType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.history.ChangeItemBean;
//import com.atlassian.jira.component.ComponentAccessor;



public class MoneyCustomField extends CalculatedCFType implements SortableCustomField {
    private static final Logger log = LoggerFactory.getLogger(MoneyCustomField.class);
    private final ChangeHistoryManager changeHistoryManager;

    public MoneyCustomField(final ChangeHistoryManager changeHistoryManager) {
    	this.changeHistoryManager = changeHistoryManager;
    }

	@Override
	public Object getSingularObjectFromString(String string) throws FieldValidationException {
		return (null == string) ? null : new Double(string);
	}

	@Override
	public String getStringFromSingularObject(Object value) {
		return (null == value) ? null : ((Double)value).toString();
	}

	@Override
	public Object getValueFromIssue(CustomField customField, Issue issue) {
		/*
		List<ChangeItemBean> assignee_changes = changeHistoryManager.getChangeItemsForField(issue, IssueFieldConstants.ASSIGNEE);

		Map<String, Integer> assignees = new HashMap<String, Integer>();
		for(ChangeItemBean change : assignee_changes){
			String assignee_key = change.getToString();
			if(assignees.containsKey(assignee_key)){
				assignees.put(assignee_key, assignees.get(assignee_key) + 1);
			}
			else{
				assignees.put(assignee_key, 1);
			}
		}
		*/
		//return assignee_changes.size();
		return 1;
	}
	
	@Override
	public Map getVelocityParameters(Issue issue, CustomField field, FieldLayoutItem fieldLayoutItem) {
		Map map = super.getVelocityParameters(issue, field, fieldLayoutItem);
		map.put("firstName", "Ali");
		
		
		List<ChangeItemBean> assignee_changes = changeHistoryManager.getChangeItemsForField(issue, IssueFieldConstants.ASSIGNEE);
		Map<String, Integer> assignees = new HashMap<String, Integer>();

		for(ChangeItemBean change : assignee_changes){
			String assignee_key = change.getToString();
			if(assignees.containsKey(assignee_key)){
				assignees.put(assignee_key, assignees.get(assignee_key) + 1);
			}
			else{
				assignees.put(assignee_key, 1);
			}
		}
		
		map.put("assignees", (HashMap) assignees);
		return map;
	}
}
