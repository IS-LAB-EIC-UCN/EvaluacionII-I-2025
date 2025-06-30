package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.core.eventbus.EventBus;

class UnitNormalizerFilterVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {
        EventBus eb = vertx.eventBus();

        eb.consumer("validated.data", message -> {
            JsonObject data = (JsonObject) message.body();

            String type = data.getString("variableType");
            double value = data.getDouble("value");

            if (type.equals("temperature") && data.containsKey("unit") && data.getString("unit").equals("F")) {
                value = (value - 32) / 1.8;
                data.put("value", value);
                data.put("unit", "C");
            }

            if (type.equals("mp") && data.containsKey("unit") && data.getString("unit").equals("mg/m3")) {
                value = value * 1000;
                data.put("value", value);
                data.put("unit", "ug/m3");
            }

            eb.publish("normalized.data", data);
        });

        startPromise.complete();
    }
}
