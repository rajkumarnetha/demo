package com.example.hello.controller;

import com.example.hello.model.Transaction;
import com.example.hello.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Controller
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;


    @RequestMapping("/transaction")
    public String transaction(Model model) {
        model.addAttribute("transactions", transactionRepository.findAll());
        return "transaction";
    }

    @RequestMapping("/createtransaction")
    public String create(Model model) {
        return "createtransaction";
    }

    @RequestMapping("/savetransaction")
    public String save(@RequestParam String name, @RequestParam int amount, String transactionType, String date) {
        Transaction transaction = new Transaction();
        BigDecimal amt = new BigDecimal(amount);
        int id = ((Collection<?>) transactionRepository.findAll()).size() + 1;

        //String yyyyMMdd = "20110914";
        DateFormat formatter =new SimpleDateFormat("yyyyMMdd");
        Date convertedDate = null;
        try {
            convertedDate = (Date) formatter.parse(date);
        }catch(ParseException ex){
            ex.printStackTrace();
        }
        transaction.setName(name);
        transaction.setAmount(amt);
        transaction.setTransactionType(transactionType);
        transaction.setId(id);
        transaction.setDate(convertedDate);
        transactionRepository.save(transaction);
        return "redirect:/showtransaction/" + transaction.getId();
    }

    @RequestMapping("/showtransaction/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("transaction", transactionRepository.findById(id).get());
        return "showtransaction";
    }

    @RequestMapping("/deletetransaction")
    public String delete(@RequestParam Integer id) {
        Transaction transaction = transactionRepository.findById(id).get();
        transactionRepository.delete(transaction);
        return "redirect:/transaction";
    }

    @RequestMapping("/edittransaction/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("transaction", transactionRepository.findById(id).get());
        return "edittransaction";
    }

    @RequestMapping("/updatetransaction")
    public String update(@RequestParam Integer id, @RequestParam String name, @RequestParam int amount, String transactionType, String date) {
        Transaction transaction = transactionRepository.findById(id).get();
        BigDecimal amt = new BigDecimal(amount);
        //String yyyyMMdd = "20110914";
        DateFormat formatter =new SimpleDateFormat("yyyyMMdd");
        Date convertedDate = null;
        try {
            convertedDate = (Date) formatter.parse(date);
        }catch(ParseException ex){
            ex.printStackTrace();
        }
        transaction.setName(name);
        transaction.setAmount(amt);
        transaction.setTransactionType(transactionType);
        transaction.setDate(convertedDate);
        transaction.setId(id);
        transactionRepository.save(transaction);
        return "redirect:/showtransaction/" + transaction.getId();
    }
}
