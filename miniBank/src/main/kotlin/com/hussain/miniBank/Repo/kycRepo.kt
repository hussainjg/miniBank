package com.hussain.miniBank.Repo

import com.hussain.miniBank.model.KYCEntity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface KycRepo : JpaRepository<KYCEntity, Long>
