package pl.furman.adcampaignmanager_backend.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance() {
        return ResponseEntity.ok(accountService.getBalance());
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody double amount) {
        try {
            accountService.deposit(amount);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestParam double amount) {
        if (accountService.withdraw(amount)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}