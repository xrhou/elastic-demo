package com.hxr.elastic.impl;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

/**
 * 员工信息聚合:
 * 1,按照country进行分组
 * 2,在每个country进行分组，再按照入职年限进行分组
 * 3,最后计算每个分组内的平均薪资
 *
 * @author houxiurong
 * @date 2019-08-24
 */
public class EmployeeAggrServiceImpl {

    public static void main(String[] args) throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "houxiurong")
                .build();

        TransportClient transportClient = new PreBuiltXPackTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        //聚合逻辑
        SearchResponse searchResponse = transportClient.prepareSearch("company")
                .addAggregation(AggregationBuilders.terms("group_by_country").field("country")
                        .subAggregation(AggregationBuilders
                                .dateHistogram("group_by_join_date")
                                .field("join_date")
                                .dateHistogramInterval(DateHistogramInterval.YEAR)
                                .subAggregation(AggregationBuilders.avg("avg_salary").field("salary")))
                ).execute().actionGet();

        Map<String, Aggregation> aggregationMap = searchResponse.getAggregations().asMap();
        StringTerms groupByCountry = (StringTerms) aggregationMap.get("group_by_country");
        Iterator<StringTerms.Bucket> groupByCountryBucketIterator = groupByCountry.getBuckets().iterator();
        while (groupByCountryBucketIterator.hasNext()) {
            StringTerms.Bucket groupByCountryBucket = groupByCountryBucketIterator.next();
            System.out.println(groupByCountryBucket.getKey() + ":" + groupByCountryBucket.getDocCount());

            Histogram groupByJoinDate = (Histogram) groupByCountryBucket.getAggregations().asMap().get("group_by_join_date");
            Iterator<Histogram.Bucket> groupByJoinDateBucketIterator = (Iterator<Histogram.Bucket>) groupByJoinDate.getBuckets().iterator();
            while (groupByJoinDateBucketIterator.hasNext()) {
                Histogram.Bucket groupByJoinDateBucket = groupByJoinDateBucketIterator.next();
                System.out.println(groupByJoinDateBucket.getKey() + ":" + groupByJoinDateBucket.getDocCount());

                Avg avg = (Avg) groupByJoinDateBucket.getAggregations().asMap().get("avg_salary");
                System.out.println(avg.getValue());
            }
        }

        //关闭连接
        transportClient.close();
    }
}
