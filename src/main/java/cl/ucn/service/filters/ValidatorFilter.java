package cl.ucn.service.filters;

import cl.ucn.domain.RawData;

public class ValidatorFilter implements RawDataFilter {


     @Override
    public RawData apply(RawData data) throws Exception {
        if (data.getType() == null || data.getTimestamp() == null || data.getValue() == 0) {
            throw new IllegalArgumentException("Incomplete data: one or more fields are null or zero");
        }
        if (!data.getType().equalsIgnoreCase("temperature") && !data.getType().equalsIgnoreCase("mp")) {
            throw new IllegalArgumentException("Invalid type: must be 'temperature' or 'mp'");
        }
        return data;
    }
}
