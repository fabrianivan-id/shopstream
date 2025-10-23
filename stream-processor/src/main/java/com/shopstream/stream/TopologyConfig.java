package com.shopstream.stream;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class TopologyConfig {
  @Bean
  public KStream<String, String> kStream(StreamsBuilder builder,
                                         @Value("${app.topics.ordersCreated}") String inTopic,
                                         @Value("${app.topics.ordersEnriched}") String outTopic) {
    var source = builder.stream(inTopic, Consumed.with(Serdes.String(), Serdes.String()));
    var enriched = source.mapValues(v -> v.replaceFirst("\}$", ",\"enriched\":true}"));
    enriched.to(outTopic, Produced.with(Serdes.String(), Serdes.String()));
    return source;
  }
}
