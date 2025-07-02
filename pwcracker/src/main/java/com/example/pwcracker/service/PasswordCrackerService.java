package com.example.pwcracker.service;

import com.example.pwcracker.model.CrackResult;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

@Service
public class PasswordCrackerService {

    // Pequeno dicionário de exemplo
    private static final List<String> DICTIONARY = Arrays.asList(
            "admin", "123456", "senha", "qwerty", "password", "letmein", "welcome", "abc123"
    );

    public CrackResult crack(String hash, String algoritmo, String ataque) {
        CrackResult result = new CrackResult();
        long start = System.currentTimeMillis();

        if ("DICTIONARY".equalsIgnoreCase(ataque)) {
            long tentativas = 0;
            for (String word : DICTIONARY) {
                tentativas++;
                if (hash.equalsIgnoreCase(hash(word, algoritmo))) {
                    result.setSucesso(true);
                    result.setSenhaEncontrada(word);
                    result.setTentativas(tentativas);
                    result.setTempoMs(System.currentTimeMillis() - start);
                    result.setMensagem("Senha encontrada via dicionário!");
                    return result;
                }
            }
            result.setSucesso(false);
            result.setTentativas(tentativas);
            result.setTempoMs(System.currentTimeMillis() - start);
            result.setMensagem("Senha não encontrada no dicionário.");
            return result;
        } else if ("BRUTE".equalsIgnoreCase(ataque)) {
            // Brute force até 4 letras minúsculas (pode ajustar, mas demora!)
            char[] charset = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
            long tentativas = 0;
            for (int len = 1; len <= 4; len++) {
                char[] pwd = new char[len];
                if (bruteRecursive(hash, algoritmo, charset, pwd, 0, len, result, start, tentativas)) {
                    result.setMensagem("Senha encontrada via brute force!");
                    return result;
                }
                tentativas = result.getTentativas();
            }
            result.setSucesso(false);
            result.setMensagem("Senha não encontrada via brute force (limite de 4 chars).");
            result.setTempoMs(System.currentTimeMillis() - start);
            return result;
        }
        result.setMensagem("Tipo de ataque inválido.");
        return result;
    }

    private boolean bruteRecursive(String targetHash, String algoritmo, char[] charset, char[] pwd, int pos, int len, CrackResult result, long start, long tentativas) {
        if (pos == len) {
            tentativas++;
            String tentativa = new String(pwd);
            if (targetHash.equalsIgnoreCase(hash(tentativa, algoritmo))) {
                result.setSucesso(true);
                result.setSenhaEncontrada(tentativa);
                result.setTentativas(tentativas);
                result.setTempoMs(System.currentTimeMillis() - start);
                return true;
            }
            result.setTentativas(tentativas);
            return false;
        }
        for (char c : charset) {
            pwd[pos] = c;
            if (bruteRecursive(targetHash, algoritmo, charset, pwd, pos + 1, len, result, start, result.getTentativas())) {
                return true;
            }
        }
        return false;
    }

    private String hash(String text, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo.replace("-", ""));
            byte[] digest = md.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }
}

