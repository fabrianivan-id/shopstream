package com.shopstream.stream;

import com.shopstream.common.events.OrderCreatedEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;

@SpringBootApplication
public class StreamProcessorApplication {
    public static void main(String[] args) { SpringApplication.run(StreamProcessorApplication.class, args); }

    @Bean
    public Topology topology() {
        StreamsBuilder b = new StreamsBuilder();
        KStream<String, OrderCreatedEvent> orders = b.stream("orders.created",
                Consumed.with(Serdes.String(), new JsonSerde<>(OrderCreatedEvent.class)));
        orders.peek((k,v)->System.out.println("Processing " + v)).to("orders.enriched");
        return b.build();
    }
}
