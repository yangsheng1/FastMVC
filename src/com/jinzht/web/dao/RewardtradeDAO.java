package com.jinzht.web.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.jinzht.web.entity.Rewardtrade;

/**
 * A data access object (DAO) providing persistence and search support for
 * Rewardtrade entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Rewardtrade
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RewardtradeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RewardtradeDAO.class);
	// property constants
	public static final String TRADE_TYPE = "tradeType";
	public static final String COUNT = "count";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Rewardtrade transientInstance) {
		log.debug("saving Rewardtrade instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Rewardtrade persistentInstance) {
		log.debug("deleting Rewardtrade instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rewardtrade findById(java.lang.Integer id) {
		log.debug("getting Rewardtrade instance with id: " + id);
		try {
			Rewardtrade instance = (Rewardtrade) getCurrentSession().get(
					"com.jinzht.web.hibernate.Rewardtrade", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Rewardtrade> findByExample(Rewardtrade instance) {
		log.debug("finding Rewardtrade instance by example");
		try {
			List<Rewardtrade> results = (List<Rewardtrade>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Rewardtrade")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Rewardtrade instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Rewardtrade as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Rewardtrade> findByTradeType(Object tradeType) {
		return findByProperty(TRADE_TYPE, tradeType);
	}

	public List<Rewardtrade> findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findAll() {
		log.debug("finding all Rewardtrade instances");
		try {
			String queryString = "from Rewardtrade";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Rewardtrade merge(Rewardtrade detachedInstance) {
		log.debug("merging Rewardtrade instance");
		try {
			Rewardtrade result = (Rewardtrade) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Rewardtrade instance) {
		log.debug("attaching dirty Rewardtrade instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rewardtrade instance) {
		log.debug("attaching clean Rewardtrade instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RewardtradeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RewardtradeDAO) ctx.getBean("RewardtradeDAO");
	}
}