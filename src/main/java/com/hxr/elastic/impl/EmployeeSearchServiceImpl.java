package com.hxr.elastic.impl;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * 员工信息搜索:
 *
 *
 *
 * @author houxiurong
 * @date 2019-08-23
 */
public class EmployeeSearchServiceImpl {

    public static void main(String[] args) throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "houxiurong")
                .build();

        TransportClient transportClient = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        //准备数据
       // prepareData(transportClient);

        //执行查询
        executeQuery(transportClient);

        //用完后关闭
        transportClient.close();
    }

    /**
     * 执行查询
     *
     * @param transportClient
     */
    private static void executeQuery(TransportClient transportClient) {
        SearchResponse searchResponse = transportClient.prepareSearch("company")
                .setTypes("employee")
                .setQuery(QueryBuilders.matchQuery("position", "technique"))
                .setPostFilter(QueryBuilders.rangeQuery("age").from(30).to(40))
                .setFrom(0).setSize(2)
                .get();
        SearchHit[] searchHit = searchResponse.getHits().getHits();
        for (int i = 0; i < searchHit.length; i++) {
            System.out.println(searchHit[i].getSourceAsString());
        }
    }

    /**
     * 准备数据
     *
     * @param transportClient
     */
    private static void prepareData(TransportClient transportClient) throws Exception {
        transportClient.prepareIndex("company", "employee", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "jack")
                        .field("age", "27")
                        .field("position", "technique software")
                        .field("country", "china")
                        .field("join_date", "2018-01-01")
                        .field("salary", "10000")
                        .endObject()
                ).get();

        transportClient.prepareIndex("company", "employee", "2")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "marry")
                        .field("age", "35")
                        .field("position", "technique software")
                        .field("country", "china")
                        .field("join_date", "2019-01-01")
                        .field("salary", "12000")
                        .endObject()
                ).get();

        transportClient.prepareIndex("company", "employee", "3")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "tom")
                        .field("age", "32")
                        .field("position", "senior technique software")
                        .field("country", "china")
                        .field("join_date", "2019-01-01")
                        .field("salary", "11000")
                        .endObject()
                ).get();

        transportClient.prepareIndex("company", "employee", "4")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "jen")
                        .field("age", "25")
                        .field("position", "junior software")
                        .field("country", "usa")
                        .field("join_date", "2019-01-01")
                        .field("salary", "7000")
                        .endObject()
                ).get();

        transportClient.prepareIndex("company", "employee", "5")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "mike")
                        .field("age", "37")
                        .field("position", "finance manager")
                        .field("country", "usa")
                        .field("join_date", "2015-01-01")
                        .field("salary", "15000")
                        .endObject()
                ).get();

    }
}
