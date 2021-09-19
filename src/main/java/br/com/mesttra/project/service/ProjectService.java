package br.com.mesttra.project.service;

import br.com.mesttra.project.data.ProjectRepository;
import br.com.mesttra.project.exception.BusinessException;
import br.com.mesttra.project.model.Budget;
import br.com.mesttra.project.model.Project;
import br.com.mesttra.project.model.Secretariat;
import br.com.mesttra.project.request.DebitExpenseRequest;
import br.com.mesttra.project.rest.BudgetClient;
import br.com.mesttra.project.rest.SecretariatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    ProjectRepository projectRepository;
    SecretariatClient secretariatClient;
    BudgetClient budgetClient;

    public ProjectService(ProjectRepository projectRepository, SecretariatClient secretariatClient, BudgetClient budgetClient) {
        this.projectRepository = projectRepository;
        this.secretariatClient = secretariatClient;
        this.budgetClient = budgetClient;
    }

    public List<Project> listProjects() {
        return this.projectRepository.findAll();
    }

    public Project addProject(Project project) throws BusinessException {

        // Um projeto deve pertencer à uma secretaria existente...
        Optional<Secretariat> secretariatOptional = secretariatClient.getSecretariat(project.getSecretariatId());
        if (secretariatOptional.isEmpty())
        {
            throw new BusinessException("Secretariat not found.");
        }
        // ... e que não esteja sob investigação
        Secretariat secretariat = secretariatOptional.get();
        if (secretariat.getUnderInvestigation())
        {
            throw new BusinessException("Secretariat is under investigation.");
        }

        // Um projeto só pode ser aprovado caso haja orçamento disponível para executá-lo,
        // orçamento este que deve ser de uma pasta condizente com a do projeto;
        String destination = project.getFolder().toString();
        List<Budget> budgets = this.budgetClient.listBudgets(destination);
        if (budgets.isEmpty())
        {
            throw new BusinessException("There is not budgets for projects on this folder.");
        }
        Budget budget = null;
        for (Budget budgetItem: budgets)
        {
            try
            {
                // Orçamento é suficiente para o projeto
                if (project.getCost() <= (budgetItem.getTotalAmount() - budgetItem.getSpentAmount()))
                {
                    DebitExpenseRequest expenseRequest = new DebitExpenseRequest(project.getCost(), project.getFolder());
                    // O gasto com o projeto deverá ser contabilizado do DB do MS de Orçamento
                    budget = this.budgetClient.debitExpense(budgetItem.getId(), expenseRequest);
                    break;
                }
            }
            catch (Exception e) {
            }
        }
        if (null == budget)
        {
            throw new BusinessException("There is no budget for projects on this folder.");
        }
        project.setBudgetId(budget.getId());

        return this.projectRepository.save(project);
    }

    public Optional<Project> findProject(Long id) { return this.projectRepository.findById(id); }
}
