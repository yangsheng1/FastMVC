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
 * Roadshowplan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roadshowplan", catalog = "jinzht2016")
public class Roadshowplan implements java.io.Serializable {

	// Fields

	private Integer financingId;
	private Timestamp beginDate;
	private Timestamp endDate;
	private Integer financeTotal;
	private Integer financedMount;
	private Set<Roadshow> roadshows = new HashSet<Roadshow>(0);

	// Constructors

	/** default constructor */
	public Roadshowplan() {
	}

	/** minimal constructor */
	public Roadshowplan(Timestamp beginDate, Timestamp endDate) {
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	/** full constructor */
	public Roadshowplan(Timestamp beginDate, Timestamp endDate,
			Integer financeTotal, Integer financedMount, Set<Roadshow> roadshows) {
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.financeTotal = financeTotal;
		this.financedMount = financedMount;
		this.roadshows = roadshows;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "financing_id", unique = true, nullable = false)
	public Integer getFinancingId() {
		return this.financingId;
	}

	public void setFinancingId(Integer financingId) {
		this.financingId = financingId;
	}

	@Column(name = "begin_date", nullable = false, length = 0)
	public Timestamp getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Timestamp beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "end_date", nullable = false, length = 0)
	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	@Column(name = "finance_total")
	public Integer getFinanceTotal() {
		return this.financeTotal;
	}

	public void setFinanceTotal(Integer financeTotal) {
		this.financeTotal = financeTotal;
	}

	@Column(name = "financed_mount")
	public Integer getFinancedMount() {
		return this.financedMount;
	}

	public void setFinancedMount(Integer financedMount) {
		this.financedMount = financedMount;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roadshowplan")
	public Set<Roadshow> getRoadshows() {
		return this.roadshows;
	}

	public void setRoadshows(Set<Roadshow> roadshows) {
		this.roadshows = roadshows;
	}

}