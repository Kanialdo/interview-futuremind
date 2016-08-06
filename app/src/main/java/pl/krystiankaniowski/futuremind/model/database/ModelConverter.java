package pl.krystiankaniowski.futuremind.model.database;

import java.util.regex.Pattern;

import pl.krystiankaniowski.futuremind.model.rest.SingleData;

public class ModelConverter {

    private static final Pattern PATTERN = Pattern.compile("http://.*");

    public static Row toDatabaseModel(SingleData data) {

        Row row = new Row();

        row.setTitle(data.getTitle());
        row.setDescription(data.getDescription());
        row.setOrderId(data.getOrderId());
        row.setImageUrl(data.getImageUrl());
        row.setUrl(data.getDescription());
        row.setModificationDate(data.getModificationDate());

        return row;

    }

}
