package com.lottery.lottery.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories("com.lottery.lottery.repositories")
@EntityScan(basePackages = ["com.lottery.lottery.entities"])
@EnableTransactionManagement
class DataConfig {

    @Bean(name = ["dataSource"])
    fun dataSource(
        @Value("\${db.mysql.url}") url: String?,
        @Value("\${db.mysql.username}") username: String?,
        @Value("\${db.mysql.password}") password: String?
    ): DataSource? {
        val dataSourceBuilder = DataSourceBuilder.create()
        //dataSourceBuilder.driverClassName("org.h2.Driver")
        dataSourceBuilder.url(url)
        dataSourceBuilder.username(username)
        dataSourceBuilder.password(password)
        return dataSourceBuilder.build()
    }
}