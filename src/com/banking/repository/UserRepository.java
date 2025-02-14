package com.banking.repository;

import com.banking.entity.Transaction;
import com.banking.entity.User;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {
    //Multiple user so -> collection is used mainly Arraylist(Duplicate) or Set(No duplication).
    //We don't want to use the duplicate value for authenticity.
    private static final Set<User> users = new HashSet<>();      //this is going to be a set which is going to store all the user data. It is going to be common that is why we need only one copy of this for efficiency soo we are making it static.
    private static final List<Transaction> transactions = new ArrayList<>();
    Map<String,Boolean> chequeBookRequest=new HashMap<>();
    // to initialize these users static member, we need a static block.
    // as we log in in we need one data already to operate that is here admin
    static {
        User user1 = new User("admin", "14242526", "admin", "admin", 550000.0);
        User user2 = new User("user2", "142652526", "user2", "user", 1000.0);
        User user3 = new User("user3", "1424255", "user3", "user", 6500.0);
        User user4 = new User("user4", "1424755", "user4", "user", 6500.0);

        //the set is considering the user3 and user4 as completely different that is why it was printing the same value again and again.
        // in order to solve that we need to use the .equals to compare the parameters which helps to differentiate the two objects.

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    public void printUsers() {
        System.out.println(users);
    }

    public User login(String username, String password) {
        List<User> finalList = users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .toList();
        if (!finalList.isEmpty()) {
            return finalList.getFirst();
        } else {
            return null;
        }
    }

    public boolean createUser(String username, String password, String contact) {
        User user = new User(username, contact, password, "user", 500.0);
        return  users.add(user);
    }

    public  Double checkBankBalance(String userId){
        List<User> result=users.stream().filter(user -> user.getUsername().equals(userId)).toList();
        if(!result.isEmpty()){
            return result.getFirst().getAccountBalance();
        }
        else{
            return null;
        }
    }
    public User getUser(String userId){
        List<User>result=users.stream().filter(user -> user.getUsername().equals(userId)).toList();
        if(!result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }
    public boolean transferMoney(String UserId, String payeeUserId, Double amount){
            boolean isdebit=debit(UserId, amount,payeeUserId);
            boolean iscredit=credit(payeeUserId, amount,UserId);
            return isdebit && iscredit;
    }

    private boolean debit(String userId,Double amount,String payeeUserId){
        User user=getUser(userId);
        Double accountBalance=user.getAccountBalance();
        users.remove(user);
        Double finalBalance=accountBalance-amount;
        user.setAccountBalance(finalBalance);
        Transaction transaction = new Transaction(
                LocalDate.now(),
                payeeUserId,
                amount,
                "debit",
                accountBalance,
                finalBalance,
                userId
        );
        System.out.println(transaction);
        transactions.add(transaction);
        return users.add(user);

    }
    private boolean credit(String payeeuserId,Double amount,String UserId){
        User user=getUser(payeeuserId);
        Double accountBalance=user.getAccountBalance();
        users.remove(user);
        Double finalBalance=accountBalance+amount;
        user.setAccountBalance(finalBalance);
        Transaction transaction = new Transaction(
                LocalDate.now(),
                UserId,
                amount,
                "credit",
                accountBalance,
                finalBalance,
                payeeuserId
        );
        System.out.println(transaction);
        transactions.add(transaction);
        return users.add(user);
    }

    public void printTransaction(String userId){
        List<Transaction> filteredtransaction = transactions.stream().filter(transaction -> transaction.getTransactionPerformedBy().equals(userId)).toList();
        System.out.println("DATE \t\t User ID \t Amount \t Type \t Initial Balance \t Final balance \t By");
        System.out.println("-------------------------------------------------------------------------------------------");
        for(Transaction t:filteredtransaction){
            System.out.println(t.getTransationDate()+"   \t"+
                    t.getTranssactionUserId()+"\t"+t.getTranssactionAmount()+"\t\t"+
                    t.getTransactionType()+"\t\t"+t.getInitialBalance()+"\t\t\t\t"+
                    t.getFinalBalance()+"\t\t"+t.getTransactionPerformedBy());
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }
    public void raiseCheckBookRequest(String userId){
        chequeBookRequest.put(userId,false);
    }
    public Map<String,Boolean> getAllChequeBookRequest(){
        return chequeBookRequest;
    }
    public List<String> getUserIdForCheckBookRequest() {
        List<String> userIds=new ArrayList<>();
        for(Map.Entry<String,Boolean> entry: chequeBookRequest.entrySet()){
            if(!entry.getValue()) {
                userIds.add(entry.getKey());
            }
        }
        return userIds;
    }
    public void approveCheckBookRequest(String userId) {
        if(chequeBookRequest.containsKey(userId)) {
           chequeBookRequest.put(userId,true);
        }
    }
}
