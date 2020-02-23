package ro.challenge.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.challenge.atm.model.Account;
import ro.challenge.atm.repository.AccountRepository;
import ro.challenge.atm.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@Controller
public class ATMController {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private AccountService accountService;

    private Account account;


    @PostMapping("/withdraw")
    public String withdraw(@RequestParam(name = "amount") BigDecimal amount) throws AmountException {
        accountService.subtractAmount(account, amount);
        return "redirect:/withdraw";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam(name = "amount") BigDecimal amount) throws AmountException {
        accountService.addAmount(account, amount);
        return "redirect:/deposit";
    }

    @RequestMapping(value = {"", "/", "balance"}, method = RequestMethod.GET)
    public ModelAndView balance(AccountRepository repository) {
        account = accountService.findById(1L);
        ModelAndView mav = new ModelAndView("welcome");
        mav.addObject("account", account);

        return mav;
    }

    @RequestMapping(value = {"/deposit", "/withdraw"}, method = RequestMethod.GET)
    public ModelAndView deposit() {
        ModelAndView mav = new ModelAndView("welcome");
        mav.addObject("account", account);
        return mav;
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @ExceptionHandler(AmountException.class)
    public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", account);
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("welcome");
        return modelAndView;
    }
}
