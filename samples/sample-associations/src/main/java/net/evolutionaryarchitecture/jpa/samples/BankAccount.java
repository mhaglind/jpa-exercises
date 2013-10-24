package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING,length=20)
@DiscriminatorValue("BA")
public class BankAccount {

    @Id
    @GeneratedValue
    Long id;

    String accountNo;

}

@Entity
@DiscriminatorValue("CC")
class CreditCard extends BankAccount {
    String creditCardNumber;
}

@Entity
@DiscriminatorValue("SA")
class SavingsAccount extends BankAccount {
    int interestRate;
}
