package com.example.Bank_Management_System.Controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Bank_Management_System.models.User;
import com.example.Bank_Management_System.repository.AccountRepository;
import com.example.Bank_Management_System.repository.PaymentRepository;
import com.example.Bank_Management_System.repository.TransactRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/transact")
public class TransactController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TransactRepository transactRepository;

    User user;
    double currentBalance;
    double newBalance;
    LocalDateTime currentDateTime = LocalDateTime.now();

    @PostMapping("/deposit")
    public String deposit(@RequestParam("deposit_amount")String depositAmount,
                          @RequestParam("account_id") String accountID,
                          HttpSession session,
                          RedirectAttributes redirectAttributes){

        //CHECK FOR EMPTY STRINGS:
        if(depositAmount.isEmpty() || accountID.isEmpty()){
            redirectAttributes.addFlashAttribute("error", "Deposit Amount or Account Depositing to Cannot Be Empty!");
            return "redirect:/app/dashboard";
        }
        // GET LOGGED IN USER:
        user = (User)session.getAttribute("user");

        // GET CURRENT ACCOUNT BALANCE:
        int acc_id = Integer.parseInt(accountID);

        double depositAmountValue = Double.parseDouble(depositAmount);

        //CHECK IF DEPOSIT AMOUNT IS 0 (ZERO):
        if(depositAmountValue == 0){
            redirectAttributes.addFlashAttribute("error", "Deposit Amount Cannot Be of 0 (Zero) Value");
            return "redirect:/app/dashboard";
        }

        //  UPDATE BALANCE:
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), acc_id);

        newBalance = currentBalance + depositAmountValue;

        // Update Account:
        accountRepository.changeAccountBalanceById(newBalance, acc_id);

        // Log Successful Transaction:
        transactRepository.logTransaction(acc_id, user.getUser_id(), "deposit", depositAmountValue, "online", "success", "Deposit Transaction Successful",currentDateTime);

        redirectAttributes.addFlashAttribute("success", "Amount Deposited Successfully");
        return "redirect:/app/dashboard";
    }
    // End Of Deposits.

    @PostMapping("/transfer")
    public String transfer(@RequestParam("transfer_from") String transfer_from,
                           @RequestParam("transfer_to") String transfer_to,
                           @RequestParam("transfer_amount")String transfer_amount,
                           HttpSession session,
                           RedirectAttributes redirectAttributes){
        // Init Error Message Value:
        String errorMessage;

        //  CHECK FOR EMPTY FIELDS:
        if(transfer_from.isEmpty() || transfer_to.isEmpty() || transfer_amount.isEmpty()){
             errorMessage = "The account transferring from and to along with the amount cannot be empty!";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        //  CONVERT VARIABLES:
        int transferFromId = Integer.parseInt(transfer_from);
        int transferToId = Integer.parseInt(transfer_to);
        double transferAmount = Double.parseDouble(transfer_amount);

        //  CHECK IF TRANSFERRING INTO THE SAME ACCOUNT:
        if(transferFromId == transferToId){
            errorMessage = "Cannot Transfer Into The same Account, Please select the appropriate account to perform transfer";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        //  CHECK FOR 0 (ZERO) VALUES:
        if(transferAmount == 0){
            errorMessage = "Cannot Transfer an amount of 0 (Zero) value, please enter a value greater than 0 (Zero) ";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        //  GET LOGGED IN USER:
        user = (User)session.getAttribute("user");

        //  GET CURRENT BALANCE:
        double currentBalanceOfAccountTransferringFrom  = accountRepository.getAccountBalance(user.getUser_id(), transferFromId);

        //  CHECK IF TRANSFER AMOUNT IS MORE THAN CURRENT BALANCE:
        if(currentBalanceOfAccountTransferringFrom < transferAmount){
            errorMessage = "You Have insufficient Funds to perform this Transfer!";
            // Log Failed Transaction:
            transactRepository.logTransaction(transferFromId, user.getUser_id(), "Transfer", transferAmount, "online", "failed", "Insufficient Funds", currentDateTime);
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        double  currentBalanceOfAccountTransferringTo = accountRepository.getAccountBalance(user.getUser_id(), transferToId);

        //  SET NEW BALANCE:
        double newBalanceOfAccountTransferringFrom = currentBalanceOfAccountTransferringFrom - transferAmount;

        double newBalanceOfAccountTransferringTo = currentBalanceOfAccountTransferringTo + transferAmount;

        // Changed The Balance Of the Account Transferring From:
        accountRepository.changeAccountBalanceById( newBalanceOfAccountTransferringFrom, transferFromId);

        // Changed The Balance Of the Account Transferring To:
        accountRepository.changeAccountBalanceById(newBalanceOfAccountTransferringTo, transferToId);

        // Log Successful Transaction:
        transactRepository.logTransaction(transferFromId, user.getUser_id(), "Transfer", transferAmount, "online", "success", "Transfer Transaction Successful",currentDateTime);

        String successMessage = "Amount Transferred Successfully!";
        redirectAttributes.addFlashAttribute("success", successMessage);
        return "redirect:/app/dashboard";
    }
    // End Of Transfer Method.

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("withdrawal_amount")String withdrawalAmount,
                           @RequestParam("account_id")String accountID,
                           HttpSession session,
                           RedirectAttributes redirectAttributes){

        String errorMessage;
        String successMessage;

        //  CHECK FOR EMPTY VALUES:
        if(withdrawalAmount.isEmpty() || accountID.isEmpty()){
            errorMessage = "Withdrawal Amount and Account Withdrawing From Cannot be Empty ";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }
        //  COVERT VARIABLES:
        double withdrawal_amount = Double.parseDouble(withdrawalAmount);
        int account_id = Integer.parseInt(accountID);

        //  CHECK FOR 0 (ZERO) VALUES:
        if (withdrawal_amount == 0){
            errorMessage = "Withdrawal Amount Cannot be of 0 (Zero) value, please enter a value greater than 0 (Zero)";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        //  GET LOGGED IN USER:
        user = (User) session.getAttribute("user");

        //  GET CURRENT BALANCE:
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), account_id);

        //  CHECK IF TRANSFER AMOUNT IS MORE THAN CURRENT BALANCE:
        if(currentBalance < withdrawal_amount){
            errorMessage = "You Have insufficient Funds to perform this Withdrawal!";
            // // Log Failed Transaction:
            transactRepository.logTransaction(account_id, user.getUser_id(), "Withdrawal", withdrawal_amount, "online", "failed", "Insufficient Funds", currentDateTime);
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        //  SET NEW BALANCE:
        newBalance = currentBalance - withdrawal_amount;

        //  UPDATE ACCOUNT BALANCE:
        accountRepository.changeAccountBalanceById(newBalance, account_id);

        // Log Successful Transaction:
        transactRepository.logTransaction(account_id, user.getUser_id(), "Withdrawal", withdrawal_amount, "online", "success", "Withdrawal Transaction Successful",currentDateTime);

        successMessage = "Withdrawal Successful!";
        redirectAttributes.addFlashAttribute("success", successMessage);
        return "redirect:/app/dashboard";
    }
    // End Of Withdrawal Method.

    @PostMapping("/payment")
    public String payment(@RequestParam("beneficiary")String beneficiary,
                          @RequestParam("account_number")String account_number,
                          @RequestParam("account_id")String account_id,
                          @RequestParam("reference")String reference,
                          @RequestParam("payment_amount")String payment_amount,
                          HttpSession session,
                          RedirectAttributes redirectAttributes){

        String errorMessage;
        String successMessage;

        //  CHECK FOR EMPTY VALUES:
        if(beneficiary.isEmpty() || account_number.isEmpty() || account_id.isEmpty() || payment_amount.isEmpty()){
            errorMessage = "Beneficiary, Account Number, Account Paying From and Payment Amount Cannot be Empty! ";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        //  CONVERT VARIABLES:
        int accountID = Integer.parseInt(account_id);
        double paymentAmount = Double.parseDouble(payment_amount);

        //  CHECK FOR 0 (ZERO) VALUES:
        if(paymentAmount == 0){
            errorMessage = "Payment Amount Cannot be of 0 (Zero) value, please enter a value greater than 0 (Zero) ";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        //  GET LOGGED IN USER:
        user = (User) session.getAttribute("user");
        

        //  GET CURRENT BALANCE:
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), accountID);

        //  CHECK IF PAYMENT AMOUNT IS MORE THAN CURRENT BALANCE:
        if(currentBalance < paymentAmount){
            errorMessage = "You Have insufficient Funds to perform this payment";
            String reasonCode = "Could not Processed Payment due to insufficient funds!";
            paymentRepository.makePayment(accountID, beneficiary, account_number, paymentAmount, reference, "failed", reasonCode, currentDateTime);
            // // Log Failed Transaction:
            transactRepository.logTransaction(accountID, user.getUser_id(),  "Payment", paymentAmount, "online", "failed", "Insufficient Funds", currentDateTime);
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/app/dashboard";
        }

        // SET NEW BALANCE FOR ACCOUNT PAYING FROM:
        newBalance = currentBalance - paymentAmount;

        //  MAKE PAYMENT:
        String reasonCode = "Payment Processed Successfully!";
        paymentRepository.makePayment(accountID, beneficiary, account_number, paymentAmount, reference, "success", reasonCode, currentDateTime);

        //  UPDATE ACCOUNT PAYING FROM:
        accountRepository.changeAccountBalanceById(newBalance, accountID);

        // Log Successful Transaction:
        transactRepository.logTransaction(accountID, user.getUser_id(), "Payment", paymentAmount, "online", "success", "Payment Transaction Successful",currentDateTime);

        successMessage = reasonCode;
        redirectAttributes.addFlashAttribute("success", successMessage);
        return "redirect:/app/dashboard";
    }
    // End Of Payment Method.

}
