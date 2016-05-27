package com.jinzht.web.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.jinzht.web.entity.Action;

/**
 * A data access object (DAO) providing persistence and search support for
 * Action entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Action
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ActionDAO {
	private static final Logger log = LoggerFactory.getLogger(ActionDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String ADDRESS = "address";
	public static final String DESCRIPTION = "description";
	public static final String INITIATE_USER = "initiateUser";
	public static final String MEMBER_LIMIT = "memberLimit";

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

	public void save(Action transientInstance) {
		log.debug("saving Action instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Action persistentInstance) {
		log.debug("deleting Action instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Action findById(java.lang.Integer id) {
		log.debug("getting Action instance with id: " + id);
		try {
			Action instance = (Action) getCurrentSession().get(
					"com.jinzht.web.hibernate.Action", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Action> findByExample(Action instance) {
		log.debug("finding Action instance by example");
		try {
			List<Action> results = (List<Action>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Action")
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
		log.debug("finding Action instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Action as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Action> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Action> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Action> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Action> findByInitiateUser(Object initiateUser) {
		return findByProperty(INITIATE_USER, initiateUser);
	}

	public List<Action> findByMemberLimit(Object memberLimit) {
		return findByProperty(MEMBER_LIMIT, memberLimit);
	}

	public List findAll() {
		log.debug("finding all Action instances");
		try {
			String queryString = "from Action";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Action merge(Action detachedInstance) {
		log.debug("merging Action instance");
		try {
			Action result = (Action) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Action instance) {
		log.debug("attaching dirty Action instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Action instance) {
		log.debug("attaching clean Action instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ActionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ActionDAO) ctx.getBean("ActionDAO");
	}
}