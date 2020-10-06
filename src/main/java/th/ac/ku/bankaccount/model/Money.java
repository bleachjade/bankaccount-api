package th.ac.ku.bankaccount.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Money {
    @NotNull @Min(0)
    private double money;


    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Money{" +
                "money=" + money +
                '}';
    }

    public double getMoney() {
        return money;
    }

}