package br.com.mesttra.project.rest;

import br.com.mesttra.project.model.Budget;
import br.com.mesttra.project.request.AuthRequest;
import br.com.mesttra.project.request.DebitExpenseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "budgets", url="http://localhost:8081/")
public interface BudgetClient {

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<String> auth(@RequestBody AuthRequest authRequest);

    @RequestMapping(value = "/budgets/?destination={destination}")
    public List<Budget> listBudgets(@RequestHeader("Authorization") String bearerToken, @PathVariable("destination") String destination);

    @RequestMapping(value = "/budgets/{id}/expense", method = RequestMethod.PATCH)
    public Budget debitExpense(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") Long id, @RequestBody DebitExpenseRequest debitExpenseRequest);

}
