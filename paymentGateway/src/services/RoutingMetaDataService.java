package services;

import constants.BankName;
import constants.PaymentMethod;
import models.RoutingMetaData;
import repository.RoutingMetaDataRepository;

public class RoutingMetaDataService {
    private final RoutingMetaDataRepository routingMetaDataRepository;

    public RoutingMetaDataService(RoutingMetaDataRepository routingMetaDataRepository) {
        this.routingMetaDataRepository = routingMetaDataRepository;
    }

    public RoutingMetaData createRoutingMeta(String bankName, String paymentMethod, Double percantage){
        RoutingMetaData routingMetaData = new RoutingMetaData(percantage, BankName.fromString(bankName), PaymentMethod.fromString(paymentMethod));
        routingMetaDataRepository.save(routingMetaData);
        return routingMetaData;
    }
}
