package cl.ucn.service.filters;

import cl.ucn.domain.RawData;

public class UnitNormalizerFilter implements RawDataFilter {

    @Override
    public RawData apply(RawData data) throws Exception {
        String unit = data.getUnit();
        double value = data.getValue();

        if (data.getType().equalsIgnoreCase("temperature")) {
            if (unit != null && unit.equalsIgnoreCase("F")) {
                double celsius = (value - 32) / 1.8;
                data.setValue(celsius);
                data.setUnit("C");
            }
        } else if (data.getType().equalsIgnoreCase("mp")) {
            if (unit != null && unit.equalsIgnoreCase("mg/m3")) {
                double micrograms = value * 1000;
                data.setValue(micrograms);
                data.setUnit("ug/m3");
            }
        }
        return data;
    }
}

