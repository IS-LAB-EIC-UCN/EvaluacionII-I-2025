package cl.ucn.service.filters;

import cl.ucn.domain.RawData;

public class ValidatorFilter implements RawDataFilter {


    @Override
    public RawData apply(RawData data) throws Exception {
        if ("temperature".equalsIgnoreCase(data.getType())) {
            double value = data.getValue();
            if (value < -50 || value > 60) {
                throw new Exception("Temperatura fuera de rango: " + value);
            }
        }
        return data;
    }
}
