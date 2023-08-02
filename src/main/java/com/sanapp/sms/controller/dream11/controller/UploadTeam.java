package com.sanapp.sms.controller.dream11.controller;

import com.sanapp.sms.domain.dream11.domain.Match;
import com.sanapp.sms.dto.ItemDetail;
import com.sanapp.sms.repository.dream11.repository.MatchRepository;
import com.sanapp.sms.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UploadTeam {

    @Autowired
    private MatchRepository matchRepository;

    @PostMapping("/uploadteam")
    public String  upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        try {
            List<Match> data = ExcelUtils.parseTeamDataExcelFile(file.getInputStream());
            matchRepository.saveAll(data);
        } catch (Exception excelException) {
            System.out.println("Exception occurred!"+excelException);;
        }
        return "teamUpload";
    }

    @GetMapping("/uploadteam")
    public String teamUploadPage(Model model) throws IOException {
        return "teamUpload";
    }
}
