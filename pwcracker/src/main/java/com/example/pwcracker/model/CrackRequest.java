package com.example.pwcracker.model;

public class CrackRequest {
    private String hash;          // Hash a ser quebrado
    private String algoritmo;     // MD5, SHA-1, SHA-256
    private String ataque;        // BRUTE, DICTIONARY

    // Getters e setters
    public String getHash() { return hash; }
    public void setHash(String hash) { this.hash = hash; }
    public String getAlgoritmo() { return algoritmo; }
    public void setAlgoritmo(String algoritmo) { this.algoritmo = algoritmo; }
    public String getAtaque() { return ataque; }
    public void setAtaque(String ataque) { this.ataque = ataque; }
}

