package br.com.mesttra.project.rest;

import br.com.mesttra.project.model.Budget;
import br.com.mesttra.project.request.DebitExpenseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "budgets", url="http://localhost:8081/")
public interface BudgetClient {

    @RequestMapping(value = "/budgets?destination={destination}")
    public List<Budget> listBudgets(@PathVariable("destination") String destination);

    @RequestMapping(value = "/budgets/{id}/expense", method = RequestMethod.PATCH)
    public Budget debitExpense(@PathVariable("id") Long id, @RequestBody DebitExpenseRequest debitExpenseRequest);

}
