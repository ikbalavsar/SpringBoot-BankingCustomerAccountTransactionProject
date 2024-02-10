package com.account.account.dto

import com.account.account.model.Customer
import com.account.account.model.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

data class AccountDto(
        val id : String?,
        val balance : BigDecimal?,
        val creationDate : LocalDateTime,
        val customer : AccountCustomerDto?,
        val transactions : Set<TransactionDto>?
)
