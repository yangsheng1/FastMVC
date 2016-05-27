package com.jinzht.web.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Action entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "action", catalog = "jinzht2016")
public class Action implements java.io.Serializable {

	// Fields

	private Integer actionId;
	private String name;
	private String address;
	private String description;
	private String initiateUser;
	private Short memberLimit;
	private Timestamp startTime;
	private Timestamp endTime;
	private Set<Actionprise> actionprises = new HashSet<Actionprise>(0);
	private Set<Attention> attentions = new HashSet<Attention>(0);
	private Set<Actioncomment> actioncomments = new HashSet<Actioncomment>(0);
	private Set<Actionshare> actionshares = new HashSet<Actionshare>(0);

	// Constructors

	/** default constructor */
	public Action() {
	}

	/** full constructor */
	public Action(String name, String address, String description,
			String initiateUser, Short memberLimit, Timestamp startTime,
			Timestamp endTime, Set<Actionprise> actionprises,
			Set<Attention> attentions, Set<Actioncomment> actioncomments,
			Set<Actionshare> actionshares) {
		this.name = name;
		this.address = address;
		this.description = description;
		this.initiateUser = initiateUser;
		this.memberLimit = memberLimit;
		this.startTime = startTime;
		this.endTime = endTime;
		this.actionprises = actionprises;
		this.attentions = attentions;
		this.actioncomments = actioncomments;
		this.actionshares = actionshares;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "action_id", unique = true, nullable = false)
	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 256)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "initiate_user", length = 256)
	public String getInitiateUser() {
		return this.initiateUser;
	}

	public void setInitiateUser(String initiateUser) {
		this.initiateUser = initiateUser;
	}

	@Column(name = "member_limit")
	public Short getMemberLimit() {
		return this.memberLimit;
	}

	public void setMemberLimit(Short memberLimit) {
		this.memberLimit = memberLimit;
	}

	@Column(name = "start_time", length = 0)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 0)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "action")
	public Set<Actionprise> getActionprises() {
		return this.actionprises;
	}

	public void setActionprises(Set<Actionprise> actionprises) {
		this.actionprises = actionprises;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "action")
	public Set<Attention> getAttentions() {
		return this.attentions;
	}

	public void setAttentions(Set<Attention> attentions) {
		this.attentions = attentions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "action")
	public Set<Actioncomment> getActioncomments() {
		return this.actioncomments;
	}

	public void setActioncomments(Set<Actioncomment> actioncomments) {
		this.actioncomments = actioncomments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "action")
	public Set<Actionshare> getActionshares() {
		return this.actionshares;
	}

	public void setActionshares(Set<Actionshare> actionshares) {
		this.actionshares = actionshares;
	}

}