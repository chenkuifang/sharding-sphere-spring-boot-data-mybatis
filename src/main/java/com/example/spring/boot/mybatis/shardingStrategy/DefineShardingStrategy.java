package com.example.spring.boot.mybatis.shardingStrategy;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * 自定义分表策略（精确分片算法） 可以参考 StandardShardingStrategy 标准分片策略
 * sharding-jdbc 提供四种分片算法：
 * 1.精确分片算法 PreciseShardingAlgorithm
 * 2.范围分片算法 RangeShardingAlgorithm
 * 3.复合分片算法 ComplexKeysShardingAlgorithm
 * 4.Hint分片算法 HintShardingAlgorithm
 *
 * @author Quifar
 */
public class DefineShardingStrategy implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        System.err.println("分片的值" + shardingValue.getValue());
        System.err.println("进入自定 义分片算法");
        return null;
    }
}
