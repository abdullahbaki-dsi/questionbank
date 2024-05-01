package org.dsi.com.approvalService.repository;

import org.dsi.com.approvalService.model.ApprovalProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalProcessRepository extends JpaRepository<ApprovalProcess, Long> {

}
