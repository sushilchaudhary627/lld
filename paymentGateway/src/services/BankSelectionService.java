package services;

import constants.BankName;
import models.Bank;
import constants.PaymentMethod;
import models.RoutingMetaData;
import repository.RoutingMetaDataRepository;

import java.util.List;
import java.util.Optional;

public class BankSelectionService {
    private final RoutingMetaDataRepository routingMetaDataRepository;

    public BankSelectionService(RoutingMetaDataRepository routingMetaDataRepository) {
        this.routingMetaDataRepository = routingMetaDataRepository;
    }

    public Optional<BankName> selectBank(PaymentMethod paymentMethod) {
        List<RoutingMetaData> routingData = routingMetaDataRepository.findByPaymentMethod(paymentMethod);

        if (routingData.isEmpty()) return Optional.empty();

        double totalPercentage = routingData.stream()
                .mapToDouble(RoutingMetaData::getPercentage)
                .sum();

        double EPSILON = 0.01;

        if (Math.abs(totalPercentage-100) > EPSILON) {
            throw new IllegalStateException("Total routing percentage must be 100%");
        }

        double randomValue = Math.random() * 100;
        double cumulativePercentage = 0.0;
        for (RoutingMetaData metaData : routingData) {
            cumulativePercentage += metaData.getPercentage();
            if (randomValue <= cumulativePercentage) {
                return Optional.of(metaData.getBankName());
            }
        }
        return Optional.empty();
    }
}
