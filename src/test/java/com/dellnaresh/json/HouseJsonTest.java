package com.dellnaresh.json;

import com.dellnaresh.db.Address;
import com.dellnaresh.db.House;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * Created by nareshm on 7/12/2015.
 */
public class HouseJsonTest {
    @Test
    public void testJsonHouse() throws Exception {
        House house=new House(1l);
        house.setAgency("McLennan");
        house.setAuctionDate(new Date());
        house.setAddressId(new Address(234l));
        house.setNoOfBedRooms("4");
        house.setPrice(100l);
        house.setStatus("Sold");
        house.setType("House");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(house);
        System.out.println(json);
        TransportClient.Builder builder = TransportClient.builder();
        TransportClient transportClient = builder.build().addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1",9300)));
        IndexRequest indexRequest = new IndexRequest("house","house",String.valueOf(house.getId()));
        indexRequest.source(json);
        IndexResponse indexResponse = transportClient.index(indexRequest).actionGet();
        System.out.println(indexResponse.isCreated());
        // MatchAll on the whole cluster with all default options
        SearchResponse response = transportClient.prepareSearch().execute().actionGet();
        System.out.println(response);

    }
}
