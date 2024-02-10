package com.account.account.dto

import com.account.account.model.Account
import javax.persistence.FetchType
import javax.persistence.OneToMany

data class CustomerDto(
        val id : String?,
        val name: String? ,
        val surname : String?,
        val accounts : Set<CustomerAccountDto>
)
