package com.ufpb.mps.equipe.grupo5.util.template;

import com.ufpb.mps.equipe.grupo5.data.service.UserAccessDataService;
import com.ufpb.mps.equipe.grupo5.model.UserAccessData;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfReport extends Report {

    private List<UserAccessData> userAccessDataList;
    private final UserAccessDataService userAccessDataService;

    public PdfReport(UserAccessDataService userAccessDataService) {
        this.userAccessDataService = userAccessDataService;
    }

    @Override
    protected void fetchData() {
        System.out.println("Buscando dados para o relatório PDF...");
        userAccessDataList = userAccessDataService.findAll().orElseThrow(() ->
                new RuntimeException("Nenhum dado de acesso encontrado.")
        );
    }

    @Override
    protected void processData() {
        System.out.println("Processando dados para o relatório PDF...");
        // Pode-se adicionar lógica para ordenar ou filtrar os dados, se necessário.
        userAccessDataList.sort((u1, u2) -> Integer.compare(u2.getAccessCount(), u1.getAccessCount()));
    }

    @Override
    protected void printReport() {
        System.out.println("Imprimindo relatório em PDF...");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("user_access_report.pdf"));
            document.open();

            // Adiciona título ao PDF
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Relatório de Acesso de Usuários", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // Adiciona um espaço entre o título e a tabela.

            // Cria uma tabela de 3 colunas
            PdfPTable table = new PdfPTable(3);

            // Cabeçalhos
            PdfPCell header1 = new PdfPCell(new Paragraph("Nome do Usuário"));
            PdfPCell header2 = new PdfPCell(new Paragraph("Número de Acessos"));
            PdfPCell header3 = new PdfPCell(new Paragraph("Data de Login"));

            table.addCell(header1);
            table.addCell(header2);
            table.addCell(header3);

            // Popula a tabela com os dados
            for (UserAccessData data : userAccessDataList) {
                table.addCell(data.getUserName());
                table.addCell(String.valueOf(data.getAccessCount()));
                table.addCell(data.getLoginDate().toString());
            }

            document.add(table);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
