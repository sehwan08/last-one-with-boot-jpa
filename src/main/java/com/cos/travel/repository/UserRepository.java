 package com.cos.travel.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.travel.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	public void deleteById(int id);

	User findByEmail(String email);
	
	Page<User> findByUsernameContaining(String username, Pageable pageable);
	Page<User> findByEmailContaining(String email, Pageable pageable);

	/* JPQL 형식
	 * @Query("SELECT u FROM User u WHERE " +
	 * "u.username LIKE %?#{escape([0])}% or " + "u.email LIKE %?#{escape([0])}% " +
	 * "escape ?#{escapeCharacter()}") List<User> findByText(String searchText);
	 */

	@Query(value = "SELECT * FROM User WHERE " + "username LIKE %?1% or " + "email LIKE %?1% ",
			countQuery = "SELECT count(*) FROM User WHERE username LIKE %?1% or email LIKE %?1%",
		    nativeQuery = true)
	Page<User> findByText(String searchText, Pageable pageable);
	//searchText = 검색어이며 ?1에 들어감
	//pageable시에는 countryQuery를 해서 개수를 알아야함
	
	
	
	
	/*
	 * @Override public List<BackupPlan> findByCondition(SearchConditionDTO
	 * searchConditionDTO) { QBackupPlan backupPlan = QBackupPlan.backupPlan;
	 * 
	 * BooleanBuilder builder = new BooleanBuilder(); String searchTextType =
	 * searchConditionDTO.getSearchTextType(); String searchText =
	 * searchConditionDTO.getSearchText(); boolean isDetailSearch =
	 * Boolean.parseBoolean(searchConditionDTO.getIsDetailSearch()); String group2 =
	 * searchConditionDTO.getGroup2(); String dateSearchType =
	 * searchConditionDTO.getDateSearchType(); String recoveryTimeFrom =
	 * searchConditionDTO.getRecoveryTimeFrom(); String recoveryTimeTo =
	 * searchConditionDTO.getRecoveryTimeTo();
	 * 
	 * if (!StringUtils.isEmpty(searchTextType) && !StringUtils.isEmpty(searchText)
	 * ){ switch(searchTextType){ case "공정":
	 * builder.and(backupPlan.group2.contains(searchText)); break; case "공장":
	 * builder.and(backupPlan.group3.contains(searchText)); break; case "생산라인":
	 * builder.and(backupPlan.vfacility.contains(searchText)); break; case "시스템명":
	 * builder.and(backupPlan.systemName.contains(searchText)); break; case "OS계열":
	 * builder.and(backupPlan.ostype.contains(searchText)); break; case "OS":
	 * builder.and(backupPlan.os.contains(searchText)); break; case "백업 예약일시":
	 * builder.and(backupPlan.backupScheduleTime.contains(searchText)); break; case
	 * "백업일시": builder.and(backupPlan.backupTime.contains(searchText)); break; case
	 * "완료여부": builder.and(backupPlan.completion.contains(searchText)); break; case
	 * "지연시간": builder.and(backupPlan.delayTime.contains(searchText)); break; case
	 * "백업사이즈": builder.and(backupPlan.size.contains(searchText)); break; case
	 * "작업자": builder.and(backupPlan.workerName.contains(searchText)); break; case
	 * "작업내용": builder.and(backupPlan.desc.contains(searchText)); break; case "전체":
	 * default: builder.or(backupPlan.group2.contains(searchText));
	 * builder.or(backupPlan.group3.contains(searchText));
	 * builder.or(backupPlan.vfacility.contains(searchText));
	 * builder.or(backupPlan.systemName.contains(searchText));
	 * builder.or(backupPlan.ostype.contains(searchText));
	 * builder.or(backupPlan.os.contains(searchText));
	 * builder.or(backupPlan.backupScheduleTime.contains(searchText));
	 * builder.or(backupPlan.backupTime.contains(searchText));
	 * builder.or(backupPlan.completion.contains(searchText));
	 * builder.or(backupPlan.delayTime.contains(searchText));
	 * builder.or(backupPlan.size.contains(searchText));
	 * builder.or(backupPlan.workerName.contains(searchText));
	 * builder.or(backupPlan.desc.contains(searchText)); } }
	 * 
	 * if( isDetailSearch ){ if( !group2.equals("전체") )
	 * builder.and(backupPlan.group2.eq(group2)); //백업일자, 백업 예약일자 조건 추가 if(
	 * "백업 예약일자".equals(dateSearchType) ) { if(recoveryTimeFrom != null &&
	 * !recoveryTimeFrom.equals(""))
	 * builder.and(backupPlan.backupScheduleTime.goe(recoveryTimeFrom));
	 * if(recoveryTimeTo != null && !recoveryTimeTo.equals(""))
	 * builder.and(backupPlan.backupScheduleTime.loe(recoveryTimeTo)); } else if(
	 * "백업일자".equals(dateSearchType) ) { if(recoveryTimeFrom != null &&
	 * !recoveryTimeFrom.equals(""))
	 * builder.and(backupPlan.backupTime.goe(recoveryTimeFrom)); if(recoveryTimeTo
	 * != null && !recoveryTimeTo.equals(""))
	 * builder.and(backupPlan.backupTime.loe(recoveryTimeTo)); } }
	 * 
	 * return queryFactory.select(Projections.fields(BackupPlan.class,
	 * backupPlan.backupPlanId, backupPlan.l2Code, backupPlan.group2,
	 * backupPlan.l3Code, backupPlan.group3, backupPlan.vfCode,
	 * backupPlan.vfacility, backupPlan.systemName, backupPlan.ostype,
	 * backupPlan.os, backupPlan.backupScheduleTime, backupPlan.backupTime,
	 * backupPlan.completion, backupPlan.delayTime, backupPlan.size,
	 * backupPlan.workerName, backupPlan.desc, backupPlan.solutionGubun,
	 * backupPlan.backupId, backupPlan.createdAt, backupPlan.modifiedAt))
	 * .from(backupPlan) .where(builder) .orderBy(backupPlan.modifiedAt.desc())
	 * .fetch(); }
	 */
}