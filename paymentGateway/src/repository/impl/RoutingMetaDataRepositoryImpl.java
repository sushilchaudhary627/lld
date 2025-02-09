package repository.impl;

import constants.PaymentMethod;
import models.RoutingMetaData;
import repository.RoutingMetaDataRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoutingMetaDataRepositoryImpl implements RoutingMetaDataRepository {
    Map<PaymentMethod, List<RoutingMetaData>> routingMetaDataMap = new HashMap<>();
    @Override
    public List<RoutingMetaData> findByPaymentMethod(PaymentMethod paymentMethod) {
        if(!routingMetaDataMap.containsKey(paymentMethod))
            return List.of();
        return routingMetaDataMap.get(paymentMethod);
    }

    @Override
    public void save(RoutingMetaData routingMetaData) {
        routingMetaDataMap.putIfAbsent(routingMetaData.getPaymentMethod(),  new ArrayList<>());
        routingMetaDataMap.get(routingMetaData.getPaymentMethod()).add(routingMetaData);
    }
}
