package org.olf.erm.usage.counter41;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

public class XMLGregorianCalendarSerializer extends StdSerializer<XMLGregorianCalendar> {

  private static final long serialVersionUID = 1L;

  XMLGregorianCalendarSerializer() {
    super(XMLGregorianCalendar.class);
  }

  @Override
  public void serialize(XMLGregorianCalendar value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    GregorianCalendar gcal = value.toGregorianCalendar();
    if (value.getXMLSchemaType().equals(DatatypeConstants.DATE)) {
      gen.writeString(gcal.toZonedDateTime().toLocalDate().toString());
    } else {
      if (value.getFractionalSecond() == null) {
        gen.writeString(
            gcal.toZonedDateTime()
                .toOffsetDateTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ")));
      } else {
        gen.writeString(
            gcal.toZonedDateTime()
                .toOffsetDateTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")));
      }
    }
  }
}
