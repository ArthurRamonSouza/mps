package com.ufpb.mps.equipe.grupo5.adapter;

import com.ufpb.mps.equipe.grupo5.model.UserAccessData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAccessAdapter {
    private UserAccessData userAccessData;

    public UserAccessAdapter(UserAccessData userAccessData) {
        this.userAccessData = userAccessData;
    }

    public String[] getFormattedData() {
        if (userAccessData == null) {
            return new String[]{"No access data available."};
        }
        
        String[] formattedData = new String[3];
        formattedData[0] = "Usuário: " + formatUserName(userAccessData.getUserName());
        formattedData[1] = "Número de Acessos: " + formatAccessCount(userAccessData.getAccessCount());
        formattedData[2] = "Último Acesso: " + formatLoginDate(userAccessData.getLoginDate());
        
        return formattedData;
    }

    private String formatUserName(String userName) {
        return (userName != null && !userName.isEmpty()) ? userName : "Nome não disponível";
    }

    private String formatAccessCount(int accessCount) {
        return accessCount >= 0 ? String.valueOf(accessCount) : "Número de acessos inválido";
    }

    private String formatLoginDate(Date loginDate) {
        if (loginDate == null) {
            return "Data de login não disponível";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(loginDate);
    }
}
