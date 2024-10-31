package com.ufpb.mps.equipe.grupo5.util.template;

import com.ufpb.mps.equipe.grupo5.business.data.service.UserAccessDataService;
import com.ufpb.mps.equipe.grupo5.model.UserAccessData;

import java.util.List;

public class HtmlReport extends Report {
    private List<UserAccessData> userAccessDataList;
    private final UserAccessDataService userAccessDataService;

    public HtmlReport(UserAccessDataService userAccessDataService) {
        this.userAccessDataService = userAccessDataService;
    }

    @Override
    protected void fetchData() {
        System.out.println("Buscando dados para o relatório HTML...");
        // Fetch the data from the service
        userAccessDataList = userAccessDataService.findAll().orElseThrow(() ->
                new RuntimeException("Nenhum dado de acesso encontrado.")
        );
    }

    @Override
    protected void processData() {
        System.out.println("Processando dados para o relatório HTML...");
        userAccessDataList.sort((u1, u2) -> Integer.compare(u2.getAccessCount(), u1.getAccessCount()));
    }

    @Override
    protected void printReport() {
        System.out.println("Imprimindo relatório em HTML...");
        StringBuilder htmlReport = new StringBuilder();
        htmlReport.append("<html><body>");
        htmlReport.append("<h1>Relatório de Acesso de Usuários</h1>");
        htmlReport.append("<table border='1'>");
        htmlReport.append("<tr><th>Nome do Usuário</th><th>Quantidade de Acessos</th><th>Data de Login</th></tr>");

        for (UserAccessData data : userAccessDataList) {
            htmlReport.append("<tr>");
            htmlReport.append("<td>").append(data.getUserName()).append("</td>");
            htmlReport.append("<td>").append(data.getAccessCount()).append("</td>");
            htmlReport.append("<td>").append(data.getLoginDate()).append("</td>");
            htmlReport.append("</tr>");
        }

        htmlReport.append("</table>");
        htmlReport.append("</body></html>");

        System.out.println(htmlReport.toString());
    }
}
