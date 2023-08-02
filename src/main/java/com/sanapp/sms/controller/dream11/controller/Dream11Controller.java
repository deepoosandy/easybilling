package com.sanapp.sms.controller.dream11.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.sanapp.sms.domain.CementDetailsReportData;
import com.sanapp.sms.domain.dream11.domain.Match;
import com.sanapp.sms.dto.dream1.dto.MatchData;
import com.sanapp.sms.dto.dream1.dto.MatchDto;
import com.sanapp.sms.repository.dream11.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class Dream11Controller {

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/teamdata/{teamcode1}/{teamcode2}")
    private void printTableOnConsole(@PathVariable("teamcode1") String teamCode1, @PathVariable("teamcode2") String teamCode2){
        List<MatchData> team1Data= getData(teamCode1);
        List<MatchData> team2Data= getData(teamCode2);


        System.out.println("============Start of Team " + teamCode1 + " ==============");
        team1Data.stream().forEach(
                td1->{
                    System.out.print(td1.getPlayerName()+"    ");
                    for(double score: td1.getScores()){
                        System.out.print(score+"   ");
                    }
                    System.out.println();
                }
        );


        System.out.println("============End of Team " + teamCode1 + " ==============");

        System.out.println("============Start of Team " + teamCode2 + " ==============");
        team2Data.stream().forEach(
                td1->{
                    System.out.print(td1.getPlayerName()+"    ");
                    for(double score: td1.getScores()){
                        System.out.print(score+"   ");
                    }
                    System.out.println();
                }
        );

        System.out.println("============End of Team " + teamCode2 + " ==============");


    }

    @GetMapping("/teamdata/{teamcode}")
    private ResponseEntity<List<MatchData>> getMatchData(@PathVariable("teamcode") String teamCode) {
        return new ResponseEntity<>(getData(teamCode), HttpStatus.OK);
    }

    private  List<MatchData> getData(String teamCode){
        List<Match> matches = matchRepository.findByTeamCode(teamCode);
        Map<String, List<Double>> matchDtoMap = matches.stream().map(domain -> {
            MatchDto dto = new MatchDto();
            dto.setPlayerScore(domain.getPlayerScore());
            dto.setPlayerName(domain.getPlayerName());
            dto.setTeamCode(domain.getTeamCode());
            return dto;
        }).collect(Collectors.groupingBy(x -> x.getPlayerName(), Collectors.mapping(m -> m.getPlayerScore(), Collectors.toList())));

        List<MatchData> matchDataList = matchDtoMap.entrySet().stream().map(data -> {
            Double[] dd = new Double[data.getValue().size()];
            int i = 0;
            for (double pp : data.getValue()) {
                dd[i] = pp;
                i++;
            }
            Arrays.sort(dd);
            return MatchData.builder().playerName(data.getKey()).scores(dd).build();
        }).collect(Collectors.toList());
       return  matchDataList.stream().sorted(
                (b1, b2) -> Arrays.compare(b2.getScores(), b1.getScores())
        ).collect(Collectors.toList());
    }

    @GetMapping(value = "/pdfReport/{team1}/{team2}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void generateCementReportPDF(HttpServletResponse response, @PathVariable("team1") String teamCode1,
                                        @PathVariable("team2") String teamCode2) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=" + teamCode1+"_"+teamCode2;
        response.setHeader(headerkey, headervalue);
        List<MatchData> team1Data= getData(teamCode1);
        List<MatchData> team2Data= getData(teamCode2);

    }

    private void createPDF(HttpServletResponse response){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
       /*     int totalQuantity= listCementDetailsDTO.stream().mapToInt(CementDetailsReportData::getCementQuantity).sum();
            addLogo(document, logoImgPath, logoImgScale);
            addDocTitle(document, reportFileName, total,totalQuantity);
            createTable(document, noOfColumns, listCementDetailsDTO, columnNames);
            addFooter(document, reportFileName);
            document.close();*/

        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
