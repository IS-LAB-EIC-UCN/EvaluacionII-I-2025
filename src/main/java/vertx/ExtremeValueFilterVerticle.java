package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.core.eventbus.EventBus;

class ExtremeValueFilterVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {
        EventBus eb = vertx.eventBus();

        eb.consumer("normalized.data", message -> {
            JsonObject data = (JsonObject) message.body();

            String type = data.getString("variableType");
            double value = data.getDouble("value");

            if (type.equals("temperature") && (value < -50 || value > 70)) {
                System.out.println("Temperatura fuera de rango descartada: " + data);
                return;
            }
            if (type.equals("mp") && (value < 0 || value > 1000)) {
                System.out.println("MP fuera de rango descartado: " + data);
                return;
            }

            eb.publish("filtered.data", data);
        });

        startPromise.complete();
    }
}