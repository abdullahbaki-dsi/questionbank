package org.dsi.com.approvalService.repository;

import org.dsi.com.approvalService.model.ApprovalStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApprovalStepRepository extends JpaRepository<ApprovalStep, Long> {



    List<ApprovalStep> findAllByApprovalProcessId(Long approvalProcessId);


//    Optional<ApprovalStep> findApprovalStepsByApprovalProcessId(Long approvalProcessId);

    List<ApprovalStep> findApprovalStepsByApprovalProcessIdOrderBySerial(Long approvalProcessId);


    List<ApprovalStep> findApprovalStepsByApprovalProcessIdInOrderBySerial(List<Long> collect);
}
