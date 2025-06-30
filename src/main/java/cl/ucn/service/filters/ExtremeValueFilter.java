package cl.ucn.service.filters;

import cl.ucn.domain.RawData;

public class ExtremeValueFilter implements RawDataFilter {

    @Override
    public RawData apply(RawData data) throws Exception {
        double value = data.getValue();
        String type = data.getType();

        if (type.equalsIgnoreCase("temperature")) {
            if (value < -50 || value > 70) {
                throw new IllegalArgumentException("Temperature out of bounds: " + value);
            }
        } else if (type.equalsIgnoreCase("mp")) {
            if (value < 0 || value > 1000) {
                throw new IllegalArgumentException("MP value out of bounds: " + value);
            }
        }
        return data;
    }
}