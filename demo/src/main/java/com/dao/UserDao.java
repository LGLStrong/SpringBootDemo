package com.dao;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.dao.customdao.UserDaoCustom;
import com.model.User;

@Repository
// ,QuerydslPredicateExecutor<User>
public interface UserDao extends CrudRepository<User, String>, PagingAndSortingRepository<User, String>, UserDaoCustom {
	// @Nullable
	/**
	 * Query Creation
	 */
	List<User> findByMc(String mc);

	// Enables the distinct flag for the query
	List<User> findDistinctUserByBzOrMc(String bz, String mc);

	List<User> findUserDistinctByBzOrMc(String bz, String mc);

	// Enabling ignoring case for an individual property
	List<User> findByMcIgnoreCase(String mc);

	// Enabling ignoring case for all suitable properties
	List<User> findByMcAndBzAllIgnoreCase(String mc, String bz);

	// Enabling static ORDER BY for a query
	List<User> findByMcOrderByMcAsc(String mc);

	List<User> findByMcOrderByMcDesc(String mc);

	// You can combine property expressions with AND and OR.
	// You also get support for operators such as Between,
	// LessThan, GreaterThan, and Like for the property expressions.

	Page<User> findByBz(String bz, Pageable pageable);

	// Slice<User> findByBz(String bz, Pageable pageable);
	//
	// List<User> findByBz(String bz, Sort sort);
	//
	// List<User> findByBz(String bz, Pageable pageable);

	/*
	 * Limiting Query Results
	 */
	User findFirstByOrderByDmAsc();

	User findTopByOrderByDmDesc();

	/*
	 * Streaming query results
	 */
	// @Query("select u from User u")
	// Stream<User> findAllByCustomQueryAndStream();
	@Async
	Future<User> findByDm(String dm);

	@Query("select u from User u where u.bz like ?1%")
	List<User> findByAndSort(String bz, Sort sort);

	@Query("select u from User u where u.dm  = ?1")
	User findBy_Dm(String dm);

	@Query(value = "SELECT ?1 FROM dual", nativeQuery = true)
	String testNativeQuery(String str);

	@Query("select u from User u where u.dm = :dm and u.bz = :bz")
	User findByDmAndBz(@Param("dm") String dm, @Param("bz") String bz);
	
	@Query("select u from #{#entityName} u where u.bz = ?1")
	List<User> findBy_Bz(String bz);
	
//	/**
//	 * CUSTOMIZE !!!!!!!!!!
//	 */
//
//	@Modifying
//	@Query("delete from User u where u.dm = ?1")
//	boolean deleteByModify(String dm);
//	// https://docs.spring.io/spring-data/jpa/docs/2.1.5.RELEASE/reference/html/ 4.6
}