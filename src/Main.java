import exception.WrongLoginExeption;
import exception.WrongPasswordExeption;

public class Main {
    private static final String VALIDATE_PATTERN = "^[a-zA-Z0-9_]+$";

    public static void main(String[] args) {
        check("login", "pass", "pass");
        check("login", "pass", "qwerty123");
        check("login11111111111111111111111111", "pass", "pass");
        check("login#@!", "pass", "pass");
        check("login", "pass#(", "pass#(");

    }

    private static boolean check(String login, String password, String confirmPassword) {
        boolean isValid = true;
        try {
            checkLogin(login);
            checkPassword(password, confirmPassword);
        } catch (WrongLoginExeption e) {
            System.out.println("Неверный логин " + e.getMessage());
            isValid = false;
        } catch (WrongPasswordExeption e) {
            System.out.println("Неверный пароль " + e.getMessage());
            isValid = false;
        }
        if (isValid) {
            System.out.println("Добро пожаловать! ");
        }
        return isValid;
    }

    private static void checkLogin(String login) throws WrongLoginExeption {
        if (!login.matches(VALIDATE_PATTERN)) {
            throw new WrongLoginExeption("Логин должен содержать в " +
                    "себе только латинские буквы, цифры и подчеркивание");
        } else if (login.length() > 20) {
            throw new WrongLoginExeption("Логин должен быть не более 20 символов");
        }
    }

    private static void checkPassword(String password, String confirmPassword) throws WrongPasswordExeption {
        if (!password.matches(VALIDATE_PATTERN)) {
            throw new WrongPasswordExeption("Пароль должен содержать в " +
                    "себе только латинские буквы, цифры и подчеркивание");
        } else if (password.length() > 20) {
            throw new WrongPasswordExeption("Пароль должен быть не более 20 символов");
        } else if (!password.equals(confirmPassword)) {
            throw new WrongPasswordExeption("Пароли должны совпадать");
        }
    }
}