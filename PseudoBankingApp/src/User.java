import java.util.HashMap;

public class User {
    HashMap<String, Object> account = new HashMap<>();

    public User(){
        account.put("Username", "admin");
        account.put("Password", "password");
        new Checking check = new Checking();
        new Savings save = new Savings();
        account.put(“Checking”,check);
        account.put(“Savings”, save);
    }

    public boolean checkUser(String usrnm){
        return accountData.get(“Username”).equalsIgnoreCase(usrnm);
    }

    public boolean checkPass(String pass){
        return accountData.get(“Password”).equals(pass);
    }

}
