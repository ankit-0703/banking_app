package com.banking.repository;

import com.banking.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRepository {
    //Multiple user so -> collection is used mainly Arraylist(Duplicate) or Set(No duplication).
    //We don't want to use the duplicate value for authenticity.
    private static final Set<User> users = new HashSet<>();      //this is going to be a set which is going to store all the user data. It is going to be common that is why we need only one copy of this for efficiency soo we are making it static.

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
            boolean isdebit=debit(UserId, amount);
            boolean iscredit=credit(payeeUserId, amount);
            return isdebit && iscredit;
    }
    private boolean debit(String userId,Double amount){
        User user=getUser(userId);
        Double accountBalance=user.getAccountBalance();
        users.remove(user);
        Double finalBalance=accountBalance-amount;
        user.setAccountBalance(finalBalance);

        return users.add(user);

    }

    private boolean credit(String userId,Double amount){
        User user=getUser(userId);
        Double accountBalance=user.getAccountBalance();
        users.remove(user);
        Double finalBalance=accountBalance+amount;
        user.setAccountBalance(finalBalance);
        return users.add(user);
    }
}
