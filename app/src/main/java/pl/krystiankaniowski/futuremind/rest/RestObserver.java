package pl.krystiankaniowski.futuremind.rest;

import java.util.List;

import pl.krystiankaniowski.futuremind.model.database.Row;

public interface RestObserver {

    void onSuccess(List<Row> data);

}
