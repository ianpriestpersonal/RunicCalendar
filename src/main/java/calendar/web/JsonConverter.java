package calendar.web;

import calendar.futharks.Rune;
import calendar.seasons.OldMonth;
import calendar.symbols.MoonPhase;
import calendar.symbols.RunicDay;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class JsonConverter {

     public static JsonObject toJson(Rune rune) {
          JsonObject to = new JsonObject();
          to.put("name", ((Enum)rune).name());
          to.put("meaning", rune.meaning());
          to.put("symbol", rune.symbol());
          to.put("magicNumber", rune.magicNumber());

          return to;
     }

     public static JsonObject toJson(RunicDay day) {
          JsonObject to = new JsonObject();
          to.put("day", toJson(day.getDay()));
          to.put("newMoon", day.getNewMoon().map(JsonConverter::toJson).orElse(null));
          to.put("moonPhase", toJson(day.getMoonPhase()));

          return to;
     }

     public static JsonArray daysToJson(List<RunicDay> days) {
          JsonArray to = new JsonArray();
          for (RunicDay day: days ) {
               to.add(toJson(day));
          }
          return to;
     }

     public static JsonArray runesToJson(List<Rune> runes) {
          JsonArray to = new JsonArray();
          for (Rune rune: runes ) {
               to.add(toJson(rune));
          }
          return to;
     }


     public static JsonObject toJson(OldMonth from) {
          JsonObject to = new JsonObject();

          to.put("oldName", from.oldName());
          to.put("meaning", from.oldMeaning());

          return to;
     }

     public static JsonObject toJson(MoonPhase from) {
          JsonObject to = new JsonObject();

          to.put("phase", from.getPhase());
          to.put("ordinal", from.getOrdinal());

          return to;
     }
}
