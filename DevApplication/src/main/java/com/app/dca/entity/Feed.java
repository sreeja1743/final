package com.app.dca.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "Feed Bean")
public class Feed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int feedId;
	
	private String queryTitle;
	private String queryQ;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate feedDate;
	 
	@JsonFormat(pattern="hh:mm:ss a")
	private LocalTime feedTime;
	
	private String topic;	// Programming/Java/Testing
	private int relevance;// Likes on Feed increase relevance, write custom query
	private int totalComments;
	
	
	@ManyToOne
	@JoinColumn(name = "devId", referencedColumnName = "devId")
	private Developer dev;
	
	@OneToMany(mappedBy = "feed")
	private List<Feedresponse> responses;
	
	
	public Feed() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feed(int feedId, String queryQ, LocalDate feedDate, LocalTime feedTime, String topic, int relevance,
			Developer dev, int totalComments, String queryTitle) {
		super();
		this.feedId = feedId;
		this.queryQ = queryQ;
		this.feedDate = feedDate;
		this.feedTime = feedTime;
		this.topic = topic;
		this.relevance = relevance;
		this.dev = dev;
		this.totalComments = totalComments;
		this.queryTitle = queryTitle;
	}
	
	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public String getQuery() {
		return queryQ;
	}
	public void setQuery(String queryQ) {
		this.queryQ = queryQ;
	}
	public LocalDate getFeedDate() {
		return feedDate;
	}
	public void setFeedDate(LocalDate feedDate) {
		this.feedDate = feedDate;
	}
	public LocalTime getFeedTime() {
		return feedTime;
	}
	public void setFeedTime(LocalTime feedTime) {
		this.feedTime = feedTime;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getRelevance() {
		return relevance;
	}
	public void setRelevance(int relevance) {
		this.relevance = relevance;
	}
	public Developer getDev() {
		return dev;
	}
	public void setDev(Developer dev) {
		this.dev = dev;
	}
	public int getTotalComments() {
		return totalComments;
	}
	public void setTotalComments(int totalComments) {
		this.totalComments = totalComments;
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dev == null) ? 0 : dev.hashCode());
		result = prime * result + ((feedDate == null) ? 0 : feedDate.hashCode());
		result = prime * result + feedId;
		result = prime * result + ((feedTime == null) ? 0 : feedTime.hashCode());
		result = prime * result + ((queryQ == null) ? 0 : queryQ.hashCode());
		result = prime * result + ((queryTitle == null) ? 0 : queryTitle.hashCode());
		result = prime * result + relevance;
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		result = prime * result + totalComments;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feed other = (Feed) obj;
		if (dev == null) {
			if (other.dev != null)
				return false;
		} else if (!dev.equals(other.dev))
			return false;
		if (feedDate == null) {
			if (other.feedDate != null)
				return false;
		} else if (!feedDate.equals(other.feedDate))
			return false;
		if (feedId != other.feedId)
			return false;
		if (feedTime == null) {
			if (other.feedTime != null)
				return false;
		} else if (!feedTime.equals(other.feedTime))
			return false;
		if (queryQ == null) {
			if (other.queryQ != null)
				return false;
		} else if (!queryQ.equals(other.queryQ))
			return false;
		if (queryTitle == null) {
			if (other.queryTitle != null)
				return false;
		} else if (!queryTitle.equals(other.queryTitle))
			return false;
		if (relevance != other.relevance)
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		if (totalComments != other.totalComments)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Feed [feedId=" + feedId + ", queryTitle=" + queryTitle + ", queryQ=" + queryQ + ", feedDate=" + feedDate
				+ ", feedTime=" + feedTime + ", topic=" + topic + ", relevance=" + relevance + ", totalComments="
				+ totalComments + ", dev=" + dev + "]";
	}

}
