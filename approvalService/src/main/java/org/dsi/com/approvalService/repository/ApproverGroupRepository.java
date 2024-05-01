package org.dsi.com.approvalService.repository;

import org.dsi.com.approvalService.model.ApproverGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproverGroupRepository extends JpaRepository<ApproverGroup,Long> {
}
