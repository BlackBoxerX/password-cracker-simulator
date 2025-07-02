package com.example.pwcracker.model;

public class CrackResult {
    private boolean sucesso;
    private String senhaEncontrada;
    private long tentativas;
    private long tempoMs;
    private String mensagem;

    // Getters e setters
    public boolean isSucesso() { return sucesso; }
    public void setSucesso(boolean sucesso) { this.sucesso = sucesso; }
    public String getSenhaEncontrada() { return senhaEncontrada; }
    public void setSenhaEncontrada(String senhaEncontrada) { this.senhaEncontrada = senhaEncontrada; }
    public long getTentativas() { return tentativas; }
    public void setTentativas(long tentativas) { this.tentativas = tentativas; }
    public long getTempoMs() { return tempoMs; }
    public void setTempoMs(long tempoMs) { this.tempoMs = tempoMs; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}
