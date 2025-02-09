package repository;

import constants.PaymentMethod;
import models.RoutingMetaData;

import java.util.List;

public interface RoutingMetaDataRepository {
    List<RoutingMetaData> findByPaymentMethod(PaymentMethod paymentMethod);

    void save(RoutingMetaData routingMetaData);
}
