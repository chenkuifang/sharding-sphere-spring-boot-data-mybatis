package com.example.spring.boot.mybatis.config;

import com.example.spring.boot.mybatis.shardingStrategy.DefineShardingStrategy;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.core.jdbc.core.datasource.ShardingDataSource;
import io.shardingsphere.jdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
import io.shardingsphere.jdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义sharding-jdbc 数据源和分片规则
 *
 * @author Quifar
 */
@Configuration
public class MyShardingDatabase {


    @Autowired
    @Qualifier("ds0")
    private DataSource ds0;

    @Autowired
    @Qualifier("ds1")
    private DataSource ds1;

    @Bean
    @Order(10)
    public DataSource dataSource () throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", ds0);
        dataSourceMap.put("ds1", ds1);

        // 配置t_order_detail表规则
        TableRuleConfiguration orderDetailTableRuleConfig = new TableRuleConfiguration();
        orderDetailTableRuleConfig.setLogicTable("t_order_detail");
        orderDetailTableRuleConfig.setActualDataNodes("ds${0..1}.t_order_detail${0..1}");
        // 自定义的分片算法实现
        StandardShardingStrategyConfiguration standardStrategy
                = new StandardShardingStrategyConfiguration("order_id", new DefineShardingStrategy());

        // 配置分表策略
        orderDetailTableRuleConfig.setTableShardingStrategyConfig(standardStrategy);

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderDetailTableRuleConfig);

        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig
                , new ConcurrentHashMap(), new Properties());
    }

}
