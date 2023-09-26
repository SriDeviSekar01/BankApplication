package com.bank;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.services.BankOperationFactory;
import com.bank.services.BankOperationType;
import com.bank.services.operations.BankOperation;
import com.bank.utils.BankConstants;
import com.bank.utils.ScannerUtils;

import java.util.*;

public class BankApplication {
    private Map<Integer, BankOperationType> bankOperationTypeMap;
    private BankOperationFactory bankOperationFactory;

    public static void main(String[] args) throws BankApplicationException {
        BankApplication bankApplication = new BankApplication();
        bankApplication.runApplication();
    }

    private void runApplication() throws BankApplicationException {
        this.instantiate();
        while (true) {
            System.out.println(BankConstants.GREETINGS);
            Scanner scanner = ScannerUtils.getScannerInstance();
            try {
                int input = scanner.nextInt();
                BankOperationType bankOperationType = this.bankOperationTypeMap.get(input);
                if (validate(bankOperationType)) continue;

                BankOperation bankOperation = this.bankOperationFactory.getOperationAndThrowErrorIfNotFound(bankOperationType);
                Object response = bankOperation.process(bankOperation.getInputAndBuildRequest(input));
                System.out.println(bankOperationType.getPostProcessLogMessage() + " : "+ response);
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Invalid input. Please try again");
                ScannerUtils.initialize();
            } catch (BankApplicationException bankApplicationException) {
                System.out.println( bankApplicationException.getMessage() +" . Please try again");
            }
        }
    }

    private static boolean validate(BankOperationType bankOperationType) {
        if (Objects.isNull(bankOperationType)) {
            System.out.println("Invalid input. Please try again");
            return true;
        }

        if (bankOperationType == BankOperationType.QUIT) {
            System.out.println(bankOperationType.getPostProcessLogMessage());
            System.exit(0);
        }
        return false;
    }

    private void instantiate() {
        this.instantiateBankOperationMap();
        BankRepository bankRepository = new BankRepository();
        this.bankOperationFactory = new BankOperationFactory(bankRepository);
    }

    private void instantiateBankOperationMap() {
        this.bankOperationTypeMap = new HashMap<>();
        this.bankOperationTypeMap.put(1, BankOperationType.CREATE_ACCOUNT);
        this.bankOperationTypeMap.put(2, BankOperationType.CREATE_ACCOUNT);
        this.bankOperationTypeMap.put(3, BankOperationType.DEPOSIT);
        this.bankOperationTypeMap.put(4, BankOperationType.WITHDRAW);
        this.bankOperationTypeMap.put(5, BankOperationType.CALCULATE_INTEREST);
        this.bankOperationTypeMap.put(6, BankOperationType.BALANCE_INQUIRY);
        this.bankOperationTypeMap.put(7, BankOperationType.QUIT);
    }
}