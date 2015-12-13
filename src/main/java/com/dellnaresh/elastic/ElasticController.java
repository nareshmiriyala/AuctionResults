package com.dellnaresh.elastic;

import com.dellnaresh.db.House;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetSocketAddress;

/**
 * Created by nareshm on 8/12/2015.
 */
public class ElasticController {
    private static Client client;
    public static boolean insertHouse(House house ){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(house);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        createTransportClient();
        IndexRequest indexRequest = new IndexRequest("house",house.getAddressId().getSuburb(),String.valueOf(house.getId()));
        indexRequest.source(json);
        IndexResponse indexResponse = client.index(indexRequest).actionGet();
        return indexResponse.isCreated();
    }

    private static void createTransportClient() {
        if(client==null) {
            TransportClient.Builder builder = TransportClient.builder();
            client = builder.build().addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
        }
    }
}
