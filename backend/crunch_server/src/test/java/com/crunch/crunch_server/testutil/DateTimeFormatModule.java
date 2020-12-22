package com.crunch.crunch_server.testutil;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class DateTimeFormatModule extends SimpleModule {
    // public DateTimeFormatModule() {
    //     super();
    //     addSerializer(DateTime.class, new JsonSerializer<DateTime>() {
    //       public final DateTimeZone TIME_ZONE = DateTimeZone.forID("Asia/Seoul");
    //       public final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss").withZone(TIME_ZONE);
    
    //       @Override      
    //       public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
    //         if (value == null) {
    //           gen.writeNull();
    //         } else {
    //           gen.writeString(FORMATTER.print(value));
    //         }
    //       }
    //     });
    
    //     addDeserializer(DateTime.class, new JsonDeserializer<DateTime>() {
    //       public final DateTimeZone TIME_ZONE = DateTimeZone.forID("Asia/Seoul");
    //       public final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withZone(TIME_ZONE);
    
    //       @Override      
    //       public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    //         JsonToken t = p.getCurrentToken();
    
    //         if (t == JsonToken.VALUE_NUMBER_INT) {
    //           return new DateTime(p.getLongValue(), TIME_ZONE);
    //         }
    
    //         if (t == JsonToken.VALUE_STRING) {
    //           String str = p.getText();
    //           if (StringUtils.isEmpty(str)) {
    //             return null;
    //           }
    //           return FORMATTER.parseDateTime(str);
    //         }
    
    //         throw ctxt.mappingException(DateTime.class);
    //       }
    //     });
    //   }
    
    
}
