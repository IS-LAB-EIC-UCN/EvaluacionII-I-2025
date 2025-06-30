package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.core.eventbus.EventBus;

public class ValidatorFilterVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        EventBus eb = vertx.eventBus();

        eb.consumer("raw.data.incoming", message -> {
            JsonObject data = (JsonObject) message.body();

            if (data.getString("variableType") == null ||
                    data.getString("timestamp") == null ||
                    data.getValue("value") == null) {
                System.out.println("Registro incompleto descartado: " + data);
                return;
            }

            String type = data.getString("variableType");
            if (!type.equals("temperature") && !type.equals("mp")) {
                System.out.println("Tipo de dato inválido descartado: " + data);
                return;
            }

            eb.publish("validated.data", data);
        });
        startPromise.complete();
    }
}