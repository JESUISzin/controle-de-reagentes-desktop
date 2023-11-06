package com.controledereagentes;

import javafx.stage.Stage;

// O padrão utilizado neste código é o "Singleton".
// O Singleton é um padrão de projeto de software que garante que uma classe
// tenha apenas uma instância e fornece um ponto global de acesso a essa instância.
public class ApplicationManager {
    private static ApplicationManager instance;
    private Stage primaryStage;

    // Construtor privado, o que significa que a classe só pode ser instanciada internamente.
    private ApplicationManager() {
    }

    // Método para obter a única instância da classe (implementação do padrão Singleton).
    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    // Método para obter o estágio principal da aplicação.
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    // Método para definir o estágio principal da aplicação.
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
