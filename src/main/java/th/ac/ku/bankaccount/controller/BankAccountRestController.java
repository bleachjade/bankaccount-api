package th.ac.ku.bankaccount.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.bankaccount.data.BankAccountRepository;
import th.ac.ku.bankaccount.model.BankAccount;
import th.ac.ku.bankaccount.model.Money;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountRestController {

    private BankAccountRepository repository;
    boolean error = false;

    public BankAccountRestController(BankAccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customer/{customerId}")
    public List<BankAccount> getAllCustomerId(@PathVariable int customerId) {
        return repository.findByCustomerId(customerId);
    }


    @GetMapping
    public List<BankAccount> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public BankAccount getOne(@PathVariable int id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public BankAccount create(@RequestBody BankAccount bankAccount) {
        BankAccount record = repository.save(bankAccount);
        repository.flush();
        return record;
    }

    @PutMapping("/{id}")
    public BankAccount update(@PathVariable int id,
                              @RequestBody BankAccount bankAccount) {
        BankAccount record = repository.findById(id).get();
        record.setBalance(bankAccount.getBalance());
        repository.save(record);
        return record;
    }

    @DeleteMapping("/{id}")
    public BankAccount delete(@PathVariable int id) {
        BankAccount record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }

    @PutMapping("/deposit/{id}")
    public BankAccount deposit(@PathVariable int id,
                               @Valid @RequestBody Money money,
                               BindingResult result,
                               Model model) {
        // test if user type negative number
        if (money.getMoney() <= 0) {
            System.out.println("Amount to be deposit should be positive");
        } else {
            BankAccount record = repository.findById(id).get();
            record.setBalance(record.getBalance() + money.getMoney());
            repository.save(record);
            return record;
        } return null;
    }

    @PutMapping("/withdraw/{id}")
    public BankAccount withdraw(@PathVariable Integer id,
                                @Valid @RequestBody Money money,
                                BindingResult result,
                                Model model) {
        if (money.getMoney() <= 0) {
            System.out.println("Amount to be withdraw should be positive");
        } else {
            BankAccount record = repository.findById(id).get();
            record.setBalance(record.getBalance() - money.getMoney());
            repository.save(record);
            return record;
        } return null;
    }
}