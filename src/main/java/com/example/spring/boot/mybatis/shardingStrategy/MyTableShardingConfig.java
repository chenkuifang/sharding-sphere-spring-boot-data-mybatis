package com.example.spring.boot.mybatis.shardingStrategy;

import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyTableShardingConfig {


    public void tableShardingConfig() {

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

    }
}
