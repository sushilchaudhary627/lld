package services;

import constants.PaymentStatus;
import dto.requests.merchant.MerchantPaymentRequest;
import models.Transaction;
import repository.TransactionRepository;

import java.util.concurrent.atomic.AtomicInteger;

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AtomicInteger transactionIdGenerator;

    public TransactionService(TransactionRepository transactionRepository, AtomicInteger transactionIdGenerator) {
        this.transactionRepository = transactionRepository;
        this.transactionIdGenerator = transactionIdGenerator;
    }

    public Transaction createTransaction(MerchantPaymentRequest transactionCreateRequest){

        Transaction transaction = new Transaction(transactionIdGenerator.getAndIncrement());
        transaction.setPaymentStatus(PaymentStatus.CREATED);
        transactionRepository.save(transaction);
        return transaction;
    }

    public void updateTransactionStatus(Transaction transaction) {
        transactionRepository.update(transaction);
    }
}
